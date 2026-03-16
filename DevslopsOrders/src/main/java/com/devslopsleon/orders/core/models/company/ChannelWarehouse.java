package com.devslopsleon.orders.core.models.company;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "channel_warehouse",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_channel_warehouse",
                columnNames = {"company_id", "sales_channel_id", "warehouse_id"}
        ),
        indexes = {
                @Index(name="idx_cw_company_channel", columnList="company_id,sales_channel_id,enabled,priority"),
                @Index(name="idx_cw_company_wh", columnList="company_id,warehouse_id")
        }
)
public class ChannelWarehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PK")
    private Long pk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="company_id", nullable=false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="sales_channel_id", nullable=false)
    private SalesChannel salesChannel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="warehouse_id", nullable=false)
    private Warehouse warehouse;

    @Column(name="enabled", nullable=false)
    private boolean enabled = true;

    // menor = más prioridad (1,2,3...)
    @Column(name="priority", nullable=false)
    private int priority = 100;

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

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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
}
