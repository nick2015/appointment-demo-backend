package com.nick.TaroDemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
@Table("appointment")
public class Appointment {

    @Id(keyType = KeyType.Generator, value = "snowFlakeId")
    @Column("ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Column("USER_ID")
    private String userId;

    @Column("SUBJECT")
    private String subject;

    @Column("START_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private ZonedDateTime startTime;

    @Column("END_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private ZonedDateTime endTime;
}
