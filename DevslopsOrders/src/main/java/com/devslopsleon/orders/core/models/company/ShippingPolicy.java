package com.devslopsleon.orders.core.models.company;


import com.devslopsleon.orders.core.models.types.mov.DeliveryMode;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "shipping_policy",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_policy",
                columnNames = {"company_id","sales_channel_id","delivery_mode_id","shipping_method_id","carrier_id"}
        ),
        indexes = {
                @Index(name="idx_policy_company_channel", columnList="company_id,sales_channel_id"),
                @Index(name="idx_policy_company_enabled", columnList="company_id,enabled")
        }
)
public class ShippingPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK")
    private Long pk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="company_id", nullable=false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="sales_channel_id", nullable=false)
    private SalesChannel salesChannel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="delivery_mode_id", nullable=false)
    private DeliveryMode deliveryMode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="shipping_method_id", nullable=false)
    private ShippingMethod shippingMethod;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="carrier_id", nullable=false)
    private DeliveryCarrier carrier;

    @Column(name="enabled", nullable=false)
    private boolean enabled = true;

    @Column(name="is_default", nullable=false)
    private boolean isDefault = false;

    @Column(name="requires_delivery_man", nullable=false)
    private boolean requiresDeliveryMan = false;

    // Si quieres reglas extra:
    @Column(name="allow_pickup", nullable=false)
    private boolean allowPickup = true;

    @Column(name="requires_external_label", nullable=false)
    private boolean requiresExternalLabel = false; // útil para drop-off y canales

    @Column(name="creation_time", nullable=false)
    private LocalDateTime creationTime;

    @Column(name="modified_time", nullable=false)
    private LocalDateTime modifiedTime;

    @PrePersist void onCreate(){ creationTime = LocalDateTime.now(); modifiedTime = creationTime; }
    @PreUpdate  void onUpdate(){ modifiedTime = LocalDateTime.now(); }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public SalesChannel getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(SalesChannel salesChannel) {
        this.salesChannel = salesChannel;
    }

    public DeliveryMode getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(DeliveryMode deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public DeliveryCarrier getCarrier() {
        return carrier;
    }

    public void setCarrier(DeliveryCarrier carrier) {
        this.carrier = carrier;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean isRequiresDeliveryMan() {
        return requiresDeliveryMan;
    }

    public void setRequiresDeliveryMan(boolean requiresDeliveryMan) {
        this.requiresDeliveryMan = requiresDeliveryMan;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public boolean isAllowPickup() {
        return allowPickup;
    }

    public void setAllowPickup(boolean allowPickup) {
        this.allowPickup = allowPickup;
    }

    public boolean isRequiresExternalLabel() {
        return requiresExternalLabel;
    }

    public void setRequiresExternalLabel(boolean requiresExternalLabel) {
        this.requiresExternalLabel = requiresExternalLabel;
    }
}
