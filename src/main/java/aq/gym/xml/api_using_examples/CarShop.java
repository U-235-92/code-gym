package aq.gym.xml.api_using_examples;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car_shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarShop {

	@XmlAttribute(name = "shop_name")
	private String shopName;
	@XmlAttribute(name =  "shop_phone")
	private String shopPhone;
	@XmlElementWrapper(name = "cars")
	@XmlElement(name = "car")
	private List<Car> cars;
	
	public CarShop() {
		super();
	}

	public CarShop(String shopName, String shopPhone, List<Car> cars) {
		super();
		this.shopName = shopName;
		this.shopPhone = shopPhone;
		this.cars = cars;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("shop name=");
		sb.append(shopName);
		sb.append(" ");
		sb.append("phone=");
		sb.append(shopPhone);
		sb.append(cars);
		return sb.toString();
	}
}
