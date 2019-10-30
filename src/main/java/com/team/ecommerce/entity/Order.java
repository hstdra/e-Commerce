package com.team.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer status;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private String address;
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;

    public Long getTotalCartPrice() {
        return orderDetails.stream().mapToLong(od -> od.getPrice() * od.getQuantity()).sum();
    }

    public Long getTotalOrderPrice() {
        return getTotalCartPrice() + getShippingFee();
    }

    public Long getShippingFee() {
        return 25000L;
    }

    private String getStatusString() {
        switch (status) {
            case 0:
                return "NULL - Giỏ hàng";
            case 1:
                return "COD - Đặt hàng thành công";
            case 2:
                return "COD - Đã xác nhận";
            case 3:
                return "COD - Đang giao hàng";
            case 4:
                return "COD - Đã nhận hàng";
            case 5:
                return "COD - Đã hủy";
            case 10:
                return "MOMO - Đang chờ thanh toán";
            case 11:
                return "MOMO - Đặt hàng thành công";
            case 12:
                return "MOMO - Đã xác nhận";
            case 13:
                return "MOMO - Đang giao hàng";
            case 14:
                return "MOMO - Đã nhận hàng";
            case 15:
                return "MOMO - Đã hủy";
            default:
                return "Null";
        }
    }

    public String getPaymentType() {
        return getStatusString().split(" - ")[0];
    }

    public String getPaymentStatus() {
        return getStatusString().split(" - ")[1];
    }
}