package com.atguigu.p2;

public class CustomerView {
	private CustomerList customerList = new CustomerList(10);
	public CustomerView() {
		Customer cust = new Customer("张三",'男',30,"010-56253825",
				"abc@email.com");
		customerList.addCustomer(cust);
	}
	//显示主菜单，响应用户输入，根据用户操作分别调用其他相应的成员方法（如addNewCustomer），以完成客户信息处理。
	public void enterMainMenu(){
		boolean loopFlag = true;
		do {
			System.out.println("-----------------客户信息管理软件-----------------");
			System.out.println("                  1 添 加 客 户");
			System.out.println("                  2 修 改 客 户");
			System.out.println("                  3 删 除 客 户");
			System.out.println("                  4 客 户 列 表");
			System.out.println("                  5 退           出");
			System.out.println();
			System.out.println("                  请选择(1-5)：");
			char key = CMUtility.readMenuSelection();
			System.out.println();
			switch (key) {
			case '1':
				addNewCustomer();
				break;
			case '2':
				modifyCustomer();
				break;
			case '3':
				deleteCustomer();
				break;
			case '4':
				listAllCustomers();
				break;
			case '5':
				System.out.println("确认是否退出(Y/N)：");
				char readConfirmSelection = CMUtility.readConfirmSelection();
				if (readConfirmSelection == 'Y') 
					loopFlag = false;
				break;
			} 
		}while (loopFlag);
	}
	private void addNewCustomer(){
		System.out.println("---------------------添加客户---------------------");
		System.out.println("姓名：");
		String name = CMUtility.readString(4);
		System.out.println("性别：");
		char sex = CMUtility.readChar();
		System.out.println("年龄：");
		int age = CMUtility.readInt();
		System.out.println("电话：");
		String phone = CMUtility.readString(11);
		System.out.println("邮箱：");
		String email = CMUtility.readString(21);
		Customer customer = new Customer(name, sex, age, phone, email);
		boolean addCustomer = customerList.addCustomer(customer);
		if (addCustomer) {
			System.out.println("---------------------添加完成---------------------");
		}else {
			System.out.println("----------------记录已满,无法添加-----------------");
		}
	}
	private void modifyCustomer(){
		System.out.println("---------------------修改客户---------------------");
		int index = 0;
		Customer customer2 = null;
		for (;;) {
			System.out.println("请选择待修改客户编号(-1退出)");
			index = CMUtility.readInt();
			if (index == -1) {
				return;
			}
			customer2 = customerList.getCustomer(index - 1);
			if (customer2 == null) {
				System.out.println("无法找到指定客户！");
			}else
				break;
		}
		System.out.println("姓名：(" + customer2.getName() + "):");
		String name = CMUtility.readString(4,customer2.getName());
		System.out.println("性别：("+customer2.getGender()+"):");
		char sex = CMUtility.readChar(customer2.getGender());
		System.out.println("年龄：("+customer2.getAge()+"):");
		int age = CMUtility.readInt(customer2.getAge());
		System.out.println("电话：("+customer2.getPhone()+"):");
		String phone = CMUtility.readString(11,customer2.getPhone());
		System.out.println("邮箱：("+customer2.getEmail()+"):");
		String email = CMUtility.readString(21,customer2.getEmail());
		customer2 = new Customer(name, sex, age, phone, email);
		boolean replaceCustomer = customerList.replaceCustomer(index - 1, customer2);
		if (replaceCustomer) {
			System.out.println("---------------------修改完成---------------------");
		}else{
			System.out.println("----------无法找到指定客户,修改失败--------------");
		}
	}
	private void deleteCustomer(){
		System.out.println("---------------------删除客户---------------------");
		int index = 0;
		Customer customer = null;
		for (;;) {
			System.out.println("请选择待删除客户编号(-1退出)：");
			index = CMUtility.readInt();
			if (index == -1) {
				return;
			}
			customer = customerList.getCustomer(index - 1);
			if (customer == null) {
				System.out.println("无法找到指定客户！");
			}else
				break;
		}
		System.out.println("确认是否删除(Y/N)：");
		char readConfirmSelection = CMUtility.readConfirmSelection();
		if (readConfirmSelection == 'N')
			return;
		
		boolean deleteCustomer = customerList.deleteCustomer(index-1);
		if (deleteCustomer) {
			System.out.println("---------------------删除完成---------------------");
		}else{
			System.out.println("----------无法找到指定客户,删除失败--------------");
		}
	}
	private void listAllCustomers(){
		System.out.println("---------------------------客户列表---------------------------");
		Customer[] allCustomers = customerList.getAllCustomers();
		if (allCustomers.length == 0) {
			System.out.println("没有客户记录！");
		}else {
			System.out.println("编号\t姓名\t性别\t年龄\t\t电话\t\t邮箱");
			for (int i = 0; i < allCustomers.length; i++) {
				System.out.println((i+1) + "\t" + allCustomers[i].getDetails());
			}
		}
		System.out.println("-------------------------客户列表完成-------------------------");
	}
	//创建CustomerView实例，并调用 enterMainMenu()方法以执行程序。
	public static void main(String[] args) {
		CustomerView customerView = new CustomerView();
		customerView.enterMainMenu();
	}
}
