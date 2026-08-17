package org.example.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ecommerce.domain.enums.Condition;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "itens")
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private UUID publicId;

    @Column(nullable = false)
    private String name;

    @Column(length = 100)
    private String description;

    @Column(columnDefinition = "TEXT", length = 2000)
    private String longDescription;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String size;

    @Enumerated(EnumType.STRING)
    private Condition itemCondition;
    private String storageLocation;

    @Column(columnDefinition = "bytea")
    @Basic(fetch = FetchType.EAGER)
    private byte[] imageData;

//    @Column(nullable = false)
    private String mimeType;

    @PrePersist
    public void prePersist() {
        if (publicId == null) {
            publicId = UUID.randomUUID();
        }
    }
}
