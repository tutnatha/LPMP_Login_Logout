/**
 *  (C) 2013-2014 Stephan Rauh http://www.beyondjava.net
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.beyondjava.jsf.sample.carshop;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean
@SessionScoped
public class CustomerBean implements Serializable {

	private static final long serialVersionUID = -5843419440559883471L;

	private Date dateOfBirth;
	
	private String emailAddress;
	
	@NotNull
	@Size(min=2, max=20)
	private String firstName;
	
	private boolean iAgreeToTheTermsAndConditions;
	
	@NotNull
	@Size(min=2, max=20)
	private String lastName;
	
	@NotNull
	@Min(100)
	@Max(999)
	private int captcha;
	
	private int expectedCaptcha;
	
	public int getExpectedCaptcha() {
		return expectedCaptcha;
	}

	private String captchaQuestion;

	private boolean showDetails=true;
	
	@PostConstruct
	private void createCaptchaQuestion() {
		expectedCaptcha = 100 + (int)Math.floor(900*Math.random());
		int summand = (int)Math.floor(expectedCaptcha*Math.random());
		int summand2 =expectedCaptcha-summand;
		captchaQuestion = "What is " + summand + " + " + summand2 + "?";
	}
	
	public boolean isiAgreeToTheTermsAndConditions() {
		return iAgreeToTheTermsAndConditions;
	}
	public void setiAgreeToTheTermsAndConditions(boolean iAgreeToTheTermsAndConditions) {
		this.iAgreeToTheTermsAndConditions = iAgreeToTheTermsAndConditions;
	}
	public int getCaptcha() {
		return captcha;
	}
	public void setCaptcha(int captcha) {
		this.captcha = captcha;
	}
	public String getCaptchaQuestion() {
		return captchaQuestion;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public boolean isIAgreeToTheTermsAndConditions() {
		return iAgreeToTheTermsAndConditions;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setIAgreeToTheTermsAndConditions(boolean iAgreeToTheTermsAndConditions) {
		this.iAgreeToTheTermsAndConditions = iAgreeToTheTermsAndConditions;
	}
	public void setLastName(String lastName) {
		if (null != lastName) lastName=lastName.toUpperCase();
		this.lastName = lastName;
	}
	
	public boolean isShowDetails() {
		return showDetails;
	}
	
	public void buy(CarBean car) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Congrats!","Thanks for buying this car!"));
		showDetails=false;
	}
	
}
