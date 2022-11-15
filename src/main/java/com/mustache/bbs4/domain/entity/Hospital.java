package com.mustache.bbs4.domain.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "nation_wide_hospitals")
//hospital이라는 테이블이 아님.
public class Hospital {

    @Id
    //GeneratedValue안씀
    private Integer id;

    @Column(name = "road_name_address")
    //컬럼에 옵션을 정할 수 있다.(컬럼 명을 매핑할 수 있음.)
    private String roadNameAddress;

    @Column(name = "hospital_name")
    private String hospitalName;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private Float totalAreaSize;
}