package org.example.ecommerce.application.useCases.item.register;

import jakarta.transaction.Transactional;
import org.example.ecommerce.application.dto.ImageValidationResult;
import org.example.ecommerce.application.extensions.StreamImageExtensions;
import org.example.ecommerce.application.mapper.ItemMapper;
import org.example.ecommerce.domain.entities.Category;
import org.example.ecommerce.domain.entities.Item;
import org.example.ecommerce.domain.enums.Condition;
import org.example.ecommerce.domain.repositories.category.CategoryRepository;
import org.example.ecommerce.domain.repositories.item.ItemRepository;
import org.example.ecommerce.domain.services.Storage.ImageStoragePort;
import org.example.ecommerce.domain.services.loggedUser.LoggedUser;
import org.example.ecommerce.shared.communication.request.item.RequestItemJson;
import org.example.ecommerce.shared.communication.response.item.ResponseRegisteredItemJson;
import org.example.ecommerce.shared.exception.ErrorOnValidationException;
import org.example.ecommerce.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class RegisterItemUseCase {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemMapper mapper;
    private final ImageStoragePort imageStorage;
    private final LoggedUser loggedUser;

    public RegisterItemUseCase(
            ItemRepository itemRepository,
            CategoryRepository categoryRepository,
            ItemMapper mapper,
            ImageStoragePort imageStorage,
            LoggedUser loggedUser
    ){
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.imageStorage = imageStorage;
        this.loggedUser = loggedUser;
    }

    @Transactional
    public ResponseRegisteredItemJson execute(
            RequestItemJson request,
            byte[] image
    ) {
        ValidateAndThrowOnFailures(request);

        var userLogged = loggedUser.getLoggedUser();

        var imageInfo = validateImage(image);

        var category = resolveCategory(request);

        var item = createItem(request, category, imageInfo.extension());

        var imageUrl = uploadImage(image, item.getPublicId());

        item.setImageUrl(imageUrl);

        itemRepository.save(item);

        return buildResponse(item);
    }

    private void ValidateAndThrowOnFailures(RequestItemJson request) {
        if (request.getCategoryId() == null && request.getNewCategoryName() == null) {
            throw new ErrorOnValidationException(
                    List.of("Passe ao menos o id ou o nome da categoria que deseja criar.")
            );
        }
    }

    private ImageValidationResult validateImage(byte[] image) {
        var result = StreamImageExtensions.validateAndGetImageExtension(image);

        if (!result.isValidImage()) {
            throw new ErrorOnValidationException(List.of("Imagem inválida."));
        }

        return result;
    }

    private Category resolveCategory(RequestItemJson request) {
        if (request.getCategoryId() != null) {
            return categoryRepository.findByUuid(request.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
        }

        var normalizedName = normalizeCategoryName(request.getNewCategoryName());

        return categoryRepository.findByName(normalizedName)
                .orElseGet(() -> {
                    var newCategory = new Category();
                    newCategory.setName(normalizedName);
                    return categoryRepository.save(newCategory);
                });
    }

    private String uploadImage(byte[] image, UUID itemId) {
        return imageStorage.upload(image, itemId);
    }

    private Item createItem(
            RequestItemJson request,
            Category category,
            String mimeType
    ) {
        var item = new Item();

        item.setCategory(category);
        item.setDescription(request.getDescription());
        item.setLongDescription(request.getLongDescription());
        item.setPrice(request.getPrice());
        item.setName(request.getName().toLowerCase());
        item.setMimeType(mimeType);
        item.setQuantity(request.getQuantity());
        item.setStorageLocation(request.getStorageLocation());

        try{
            item.setItemCondition(
                    Condition.valueOf(request.getItemCondition().toUpperCase())
            );
        }catch (IllegalArgumentException e){
            throw new ErrorOnValidationException(List.of("Condição inválda: " + request.getItemCondition()));
        }

        return item;
    }

    private ResponseRegisteredItemJson buildResponse(Item item) {
        return ResponseRegisteredItemJson.builder()
                .name(item.getName())
                .description(item.getDescription())
                .longDescription(item.getLongDescription())
                .publicId(item.getPublicId())
                .category(item.getCategory().getName())
                .imageUrl(item.getImageUrl())
                .build();
    }

    private String normalizeCategoryName(String categoryName) {
        var normalized = Normalizer.normalize(categoryName, Normalizer.Form.NFD);

        Pattern pattern = Pattern.compile("\\p{M}+");

        return pattern.matcher(normalized)
                .replaceAll("")
                .toLowerCase()
                .trim();
    }
}
