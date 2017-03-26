package ie.sugrue.domain;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Product {

	private final Logger	log	= LoggerFactory.getLogger(this.getClass());

	private long			id;
	private String			category_id, name, description, icon_url, image_url;
	private BigDecimal		price;
	private int				stock;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", category_id=" + category_id + ", name=" + name + ", description=" + description + ", icon_url=" + icon_url + ", image_url=" + image_url
				+ ", price=" + price + ", stock=" + stock + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category_id == null) ? 0 : category_id.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((icon_url == null) ? 0 : icon_url.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((image_url == null) ? 0 : image_url.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + stock;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Product other = (Product) obj;
		if (category_id == null) {
			if (other.category_id != null) {
				return false;
			}
		} else if (!category_id.equals(other.category_id)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (icon_url == null) {
			if (other.icon_url != null) {
				return false;
			}
		} else if (!icon_url.equals(other.icon_url)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (image_url == null) {
			if (other.image_url != null) {
				return false;
			}
		} else if (!image_url.equals(other.image_url)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (stock != other.stock) {
			return false;
		}
		return true;
	}

}
