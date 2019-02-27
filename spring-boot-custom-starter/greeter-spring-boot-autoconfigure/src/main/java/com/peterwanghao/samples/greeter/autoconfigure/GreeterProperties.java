package com.peterwanghao.samples.greeter.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName: GreeterProperties
 * @Description:TODO(������һ�仰��������������)
 * @author: wanghao
 * @date: 2019��2��27�� ����10:07:42
 * @version V1.0
 * 
 */
@ConfigurationProperties(prefix = "peterwanghao.samples.greeter")
public class GreeterProperties {
	private String userName;
	private String morningMessage;
	private String afternoonMessage;
	private String eveningMessage;
	private String nightMessage;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMorningMessage() {
		return morningMessage;
	}

	public void setMorningMessage(String morningMessage) {
		this.morningMessage = morningMessage;
	}

	public String getAfternoonMessage() {
		return afternoonMessage;
	}

	public void setAfternoonMessage(String afternoonMessage) {
		this.afternoonMessage = afternoonMessage;
	}

	public String getEveningMessage() {
		return eveningMessage;
	}

	public void setEveningMessage(String eveningMessage) {
		this.eveningMessage = eveningMessage;
	}

	public String getNightMessage() {
		return nightMessage;
	}

	public void setNightMessage(String nightMessage) {
		this.nightMessage = nightMessage;
	}
}
