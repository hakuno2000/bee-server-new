package com.example.bee.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creator_user_id", nullable = true)
    private Long creatorUserId;

    @Column(name = "item", nullable = true)
    private String item;

    @Column(name = "OrderNo", nullable = true)
    private String OrderNo;

    @Column(name = "value", nullable = true)
    private Long value;

    @Column(name = "weight", nullable = true)
    private Double weight;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "status", nullable = true)
    private long status;

    @Column(name = "sender_name", nullable = true)
    private String senderName;

    @Column(name = "sender_phone", nullable = true)
    private String senderPhone;

    @Column(name = "sender_address", nullable = true)
    private String senderAddress;

    @Column(name = "receiver_name", nullable = true)
    private String receiverName;

    @Column(name = "receiver_phone", nullable = true)
    private String receiverPhone;

    @Column(name = "receiver_address", nullable = true)
    private String receiverAddress;

    @Column(name = "ordered_date", nullable = true)
    private LocalDate orderedDate;

    @Column(name = "sent_date", nullable = true)
    private LocalDate sentDate;

    @Column(name = "received_date", nullable = true)
    private LocalDate receivedDate;

    @Column(name = "sent_date_real", nullable = true)
    private LocalDate sentDateReal;

    @Column(name = "received_date_real", nullable = true)
    private LocalDate receivedDateReal;

    @Column(name = "distance", nullable = true)
    private String distance;

    @Column(name = "fee", nullable = true)
    private String fee;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "shipper_id", referencedColumnName = "id")
    private Account account;
    @Transient
    private Account user;
}
