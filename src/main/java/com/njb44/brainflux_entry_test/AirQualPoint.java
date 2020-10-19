package com.njb44.brainflux_entry_test;

import java.time.Instant;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "AirQualPoint")
public class AirQualPoint {
	@Column(name = "time")
	private Instant time;
	@Column(name = "CO(GT)")
	private Double CO;
	@Column(name = "PT08.S1(CO)")
	private int S1_CO;
	@Column(name = "NMHC(GT)")
	private int NMHC;
	@Column(name = "C6H6(GT)")
	private Double C6H6;
	@Column(name = "PT08.S2(NMHC)")
	private int S2_NMHC;
	@Column(name = "NOx(GT)")
	private int NOx;
	@Column(name = "S3(NOx)")
	private int S3_NOx;
	@Column(name = "NO2(GT)")
	private int NO2;
	@Column(name = "PT08.S4(NO2)")
	private int S4_NO2;
	@Column(name = "PT08.S5(O3)")
	private int S5_O3;
	@Column(name = "T")
	private Double T;
	@Column(name = "RH")
	private Double RH;
	@Column(name = "AH")
	private Double AH;
	public Instant getTime() {
		return time;
	}
	public void setTime(Instant time) {
		this.time = time;
	}
	public Double getCO() {
		return CO;
	}
	public void setCO(Double cO) {
		CO = cO;
	}
	public int getS1_CO() {
		return S1_CO;
	}
	public void setS1_CO(int s1_CO) {
		S1_CO = s1_CO;
	}
	public int getNMHC() {
		return NMHC;
	}
	public void setNMHC(int nMHC) {
		NMHC = nMHC;
	}
	public Double getC6H6() {
		return C6H6;
	}
	public void setC6H6(Double c6h6) {
		C6H6 = c6h6;
	}
	public int getS2_NMHC() {
		return S2_NMHC;
	}
	public void setS2_NMHC(int s2_NMHC) {
		S2_NMHC = s2_NMHC;
	}
	public int getNOx() {
		return NOx;
	}
	public void setNOx(int nOx) {
		NOx = nOx;
	}
	public int getS3_NOx() {
		return S3_NOx;
	}
	public void setS3_NOx(int s3_NOx) {
		S3_NOx = s3_NOx;
	}
	public int getNO2() {
		return NO2;
	}
	public void setNO2(int nO2) {
		NO2 = nO2;
	}
	public int getS4_NO2() {
		return S4_NO2;
	}
	public void setS4_NO2(int s4_NO2) {
		S4_NO2 = s4_NO2;
	}
	public int getS5_O3() {
		return S5_O3;
	}
	public void setS5_O3(int s5_O3) {
		S5_O3 = s5_O3;
	}
	public Double getT() {
		return T;
	}
	public void setT(Double t) {
		T = t;
	}
	public Double getRH() {
		return RH;
	}
	public void setRH(Double rH) {
		RH = rH;
	}
	public Double getAH() {
		return AH;
	}
	public void setAH(Double aH) {
		AH = aH;
	}
}