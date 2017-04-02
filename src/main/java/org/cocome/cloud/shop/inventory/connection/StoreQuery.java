package org.cocome.cloud.shop.inventory.connection;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.cocome.cloud.logic.registry.client.IApplicationHelper;
import org.cocome.cloud.logic.stub.IStoreManager;
import org.cocome.cloud.logic.stub.IStoreManagerService;
import org.cocome.cloud.logic.stub.NotBoundException_Exception;
import org.cocome.cloud.logic.stub.NotInDatabaseException_Exception;
import org.cocome.cloud.registry.service.Names;
import org.cocome.cloud.shop.inventory.store.ProductWrapper;
import org.cocome.cloud.shop.inventory.store.Store;
import org.cocome.tradingsystem.inventory.application.store.ProductWithSupplierAndStockItemTO;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Implements the store query interface to retrieve store related information.
 * Uses the web service interface from CoCoMEs logic.
 * 
 * @author Tobias Pöppke
 * @author Robert Heinrich
 */
@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StoreQuery {
	private static final Logger LOG = Logger.getLogger(StoreQuery.class);
	
	IStoreManager storeManager;
	
	long defaultStoreIndex = -1;
	
	@Inject
	IApplicationHelper applicationHelper;
	
	private IStoreManager lookupStoreManager(long storeID) throws NotInDatabaseException_Exception {
		try {
			return applicationHelper.getComponent(
					Names.getStoreManagerRegistryName(storeID), 
					IStoreManagerService.SERVICE, 
					IStoreManagerService.class).getIStoreManagerPort();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| MalformedURLException | NoSuchMethodException | SecurityException | NotBoundException_Exception e) {
			if (storeID == defaultStoreIndex) {
			LOG.error("Got exception while retrieving store manager location: " + e.getMessage());
			e.printStackTrace();
			throw new NotInDatabaseException_Exception(e.getMessage());
			} else {
				return lookupStoreManager(defaultStoreIndex);
			}
		}
	}
	
	public List<ProductWrapper> queryStockItems(Store store) throws NotInDatabaseException_Exception {
		storeManager = lookupStoreManager(store.getID());
		List<ProductWrapper> stockItems = new LinkedList<ProductWrapper>();
		List<ProductWithSupplierAndStockItemTO> items = storeManager.getProductsWithStockItems(store.getID());
		for (ProductWithSupplierAndStockItemTO item : items) {
			ProductWrapper newItem = new ProductWrapper(item, item.getStockItemTO(), store);
			stockItems.add(newItem);
		}
		return stockItems;
	}

//	@Override
//	public ProductWrapper getStockItemByProductID(Store store, long productID) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public ProductWrapper getStockItemByBarcode(Store store, long barcode) {
//		return null;
//	}

//	@Override
//	public boolean accountSale(IShoppingCart cart) throws NotInDatabaseException_Exception, ProductOutOfStockException_Exception, UpdateException_Exception {
//		LinkedHashMap<Long, SaleTO> saleByStore = new LinkedHashMap<>((int) (cart.getItemCount() / 0.75));
//		
//		for (CartItem item : cart.getItems()) {
//			SaleTO sale = saleByStore.get(item.getStore().getID());
//			
//			if (sale == null) {
//				sale = new SaleTO();
//				List<ProductWithStockItemTO> productTOs = new LinkedList<>();
//				sale.setProductTOs(productTOs);
//				saleByStore.put(item.getStore().getID(), sale);
//			}
//			
//			sale.setDate(new Date());
//			
//			for (int i = 0; i < item.getQuantity(); i++) {
//				ProductWithStockItemTO product = new ProductWithStockItemTO();
//				product.setBarcode(item.getProduct().getBarcode());
//				product.setPurchasePrice(item.getProduct().getSalesPrice());
//				product.setStockItemTO(item.getProduct().getStockItemTO());
//				sale.getProductTOs().add(product);
//			}
//			
//
//		}
//		
//		for (Entry<Long, SaleTO> sale : saleByStore.entrySet()) {
//			storeManager = lookupStoreManager(sale.getKey());
//			storeManager.accountSale(sale.getKey(), sale.getValue());
//		}
//		
//		return true;
//	}
}
