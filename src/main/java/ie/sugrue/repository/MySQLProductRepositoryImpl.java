package ie.sugrue.repository;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ie.sugrue.domain.Product;

@Repository
public class MySQLProductRepositoryImpl implements ProductRepository {

	private final Logger	log	= LoggerFactory.getLogger(this.getClass());
	@Autowired
	protected JdbcTemplate	jdbc;

	public void createProduct(Product product) {
		String SQL = "insert into product ( id, category_id, name, description, price, stock, icon_url, image_url ) values (?, ?, ?, ?, ?, ?, ?, ?)";

		jdbc.update(SQL, product.getId(), product.getCategory_id(), product.getName(), product.getDescription(), product.getPrice(), product.getStock(), product.getIcon_url(),
				product.getImage_url());
		log.debug("Created Record Name = " + product.getId() + " " + product.getName());
		return;
	}

	public Product getProduct(long id) {
		String SQL = "select * from product where id = ?";
		Product product = jdbc.queryForObject(SQL, new Object[] { id }, new ProductMapper());
		return product;
	}

	public ArrayList<Product> getProducts(String category_id) {
		String SQL = "select * from product where category_id = ? order by name";
		ArrayList<Product> products = new ArrayList<Product>();
		products.addAll(jdbc.query(SQL, new Object[] { category_id }, new ProductMapper()));
		return products;
	}

	public ArrayList<Product> getProducts() {
		String SQL = "select * from product order by name";
		ArrayList<Product> products = new ArrayList<Product>();
		products.addAll(jdbc.query(SQL, new ProductMapper()));
		return products;
	}

	public void deleteProduct(long id) {
		String SQL = "delete from product where id = ?";
		int result = jdbc.update(SQL, id);
		if (result == 0) {
			throw new EmptyResultDataAccessException(1);
		}
		log.debug("Deleted Record with ID = " + id);
		return;
	}

	public Product updateProduct(Product product) {
		// user = populateUserNullsWithdefaults(user);

		String SQL = "update product set category_id = ?, name = ?, description = ?, price = ?, stock = ?, icon_url = ?, image_url = ? where id = ?";
		jdbc.update(SQL, product.getCategory_id(), product.getName(), product.getDescription(), product.getPrice(), product.getStock(), product.getIcon_url(),
				product.getImage_url(), product.getId());
		product = getProduct(product.getId());
		log.debug("Updated Record with ID = " + product.getId());
		return product;
	}

}