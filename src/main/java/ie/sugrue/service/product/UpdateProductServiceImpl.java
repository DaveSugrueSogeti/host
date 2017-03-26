package ie.sugrue.service.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ie.sugrue.domain.Product;
import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.repository.ProductRepository;

@Service("updateProductService")
@Scope("prototype")
public class UpdateProductServiceImpl implements UpdateProductService {

	private final Logger		log	= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductRepository	productRepo;

	@Override
	public ResponseWrapper updateProduct(ResponseWrapper resp, Product product) {

		try {
			resp.addObject(productRepo.updateProduct(product));
		} catch (EmptyResultDataAccessException erdae) {
			log.error("Cannot identify user to be updated when trying to update {} to Database", product, erdae);
			resp.getStatus().updateStatus(1, "We encountered a problem saving the Product details to our database. Please try again.");
		} catch (DataAccessException dae) {
			log.error("Data Access exception encountered trying to update {} to Database", product, dae);
			resp.getStatus().updateStatus(2, "We encountered a problem saving the Product details to our database. Please try again.");
		} catch (Exception e) {
			log.error("Unknown exception encountered trying to update {} to Database", product, e);
			resp.getStatus().updateStatus(2, "We encountered a problem saving the Product details to our database. Please try again.");
		}

		return resp;
	}

}
