package pageUIs.maybe;

public class CartUI {

	public static final String NAME_FIRST_PRODUCT = "(//h3[@class='product-name'])[1]/a";
	public static final String EMPTY_CART_TEXT = "//p[text()='	Về trang cửa hàng để chọn mua sản phẩm bạn nhé!!']";
	public static final String DEL_PRODUCT_ICON = "//a[@title='Xóa']";
	public static final String INCREASE_QUANTITY = "//button[@class='increase items-count btn-plus btn']";
	public static final String DECREASE_QUANTITY = "//button[@class='reduced items-count btn-minus btn']";
	public static final String SENDKEY_QUANTITY = "//input[@name='updates[]']";
	public static final String CLICK_SPACE = "//div[@class='header-cart']";
	public static final String ORDER_BUTTON = "//button[@class='btn btn-block btn-proceed-checkout-mobile']";
}
