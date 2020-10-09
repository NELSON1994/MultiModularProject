package com.nelson.rokel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
@Author:Nelson
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TMS_DEVICE_HEARTBEAT")
public class PosIris implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ONLINE_ACTIVITY_SEQ")
    @SequenceGenerator(sequenceName = "tms_device_heartbeat_seq", allocationSize = 1, name = "TMS_DEVICE_HEARTBEAT_SEQ")
    @Column(name = "LOG_ID")
    private Long id;
    @Column(name = "SERIAL_NO")
    private String serialNumber;
    @Column(name = "APPLICATION_VERSION")
    private String appVersion;
    @Column(name = "OS_VERSION")
    private String osVersion;
    @Column(name = "BATTERY_PERCENTAGE")
    private String batteryLevel;
    @Column(name = "CHARGING_STATUS")
    private String chargingStatus;
    @Column(name = "SIGNAL_STRENGTH")
    private String signalStrength;
    @Column(name = "DEVICE_TEMPERATURE")
    private String terminalTemperature;
    @Column(name = "TM_VERSION")
    private String terminalVersion;
    @Column(name = "TID")
    private String tid;
    @Column(name = "OBJ")
    private String obj;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
}
