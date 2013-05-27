package com.waiterhelper.database;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.waiterhelper.OrdersAdapter.OnCheckedChangeListenerCustom;

public class DataBase {
	
	// Let's do this thing singleton style
	private static DataBase instance = new DataBase();
	
	private ArrayList<Entry> tables = new ArrayList<Entry>();
	
	// Node Class for ListView of Tables
	public class Entry {
		
		public String tableName;
		public LinkedList<Order> orders = null;
		
		public Entry(){
			orders = new LinkedList<Order>();
			tableName = "New Table";
		}
		
		public String toString(){
			return this.tableName;
		}
		
		public Entry(String name, String order){
			tableName = name;
			orders = new LinkedList<Order>();
			orders.add(new Order(order));
		}
		
		public Entry(String name, String [] ordersIn){
			this.tableName = name;
			orders = new LinkedList<Order>();
			
			for(String s : ordersIn) {
				orders.add(new Order(s));
			}
			
		}
		
		public void addOrder(String newOrder) {
			orders.add(new Order(newOrder));
		}
		
		public void removeOrder(String toRemove) {
			orders.remove(toRemove);
		}
		
		public void clearOrders() {
			orders = null;
		}
		
		public String getName(){
			return tableName;
		}
		
		public void setName(String name){
			tableName = name;
		}
		
	}
	
	public class Order {
		
		private LinkedList<String> additions;
		private String orderName;
		private boolean selected = false;
		public OnCheckedChangeListenerCustom checkListener;
		
		public void changeName(String tempName) {
			orderName = tempName;
		}
		
		public boolean isSelected() {
			return selected;
		}
		
		public void changeSelected(){
			if(selected)
				selected = false;
			else
				selected = true;
		}
		
		public Order(){
			this.additions = new LinkedList<String>();
			this.orderName = "New Order";
		}
		
		public Order(String name) {
			this.additions = new LinkedList<String>();
			this.orderName = name;
		}
		
		public Order(String name, String newAddition) {
			this.additions = new LinkedList<String>();
			this.orderName = name;
			this.additions.add(newAddition);
		}
		
		public Order(String name, LinkedList<String> newAddition){
			this.additions = new LinkedList<String>();
			this.additions = newAddition;
			this.orderName = name;
		}
		
		public void clearAdditions() {
			this.additions = null;
			this.additions = new LinkedList<String>();
		}
		
		public void removeAddition(int position){
			this.additions.remove(position);
		}
		
		public void removeAddition(String name){
			this.additions.remove(name);
		}
		
		public void addAddition(String name) {
			this.additions.add(name);
		}
		
		public void addAddition(LinkedList<String> newAddition){
			for(String s : newAddition){
				this.additions.add(s);
			}
		}
		
		public void addAddition(List<String> newAddition){
			for(String s : newAddition){
				this.additions.add(s);
			}
		}
		
		public boolean hasAddons() {
			return !(this.additions.isEmpty());
		}
		
		public List<String> getAdditions() {
			return this.additions;
		}
		
		public void clear() {
			this.orderName = null;
			this.additions = null;
		}
		
		public void setName(String name) {
			orderName = name;
		}
		
		public String getName() {
			return orderName;
		}
		
	}
	
	// Private Constructor for Singleton Pattern
	private DataBase(){/* Nothing needed here */}

	// Returns Running Instance of DataBase
	public static DataBase getInstance(){
		return instance;		
	}
	
	public ArrayList<Entry> getList() {
		return tables;
	}
	
	public String[] getStringList(){
		String [] stringNames = new String[tables.size()];
		
		for(int i=0; i<tables.size(); i++){
			stringNames[i] = (tables.get(i)).toString();
		}
		
		return stringNames;
	}
	
	public Entry get(int position){
		return tables.get(position);
	}
	
	public void testPopulate(){
		
		tables.add(new Entry("goose1","oranges"));
		tables.add(new Entry("goose2","apples"));
		String [] temp = {"apples","blueberries","noodles", "cadfadsfdafdsafsafdsafdsafdsafdsafdsafdsafdsafdsafddsafasfdsasaf", "adfdsaf","geese","filler"};
		tables.add(new Entry("Table3",temp));
		LinkedList <String> test = new LinkedList<String>();
		test.add("gooseBerry");
		test.add("chickenTarts");
		tables.get(2).orders.get(0).addAddition("Alright");
		tables.get(2).orders.get(2).addAddition(test);
		
	}
	
	public void add(Entry e){
		tables.add(e);
	}
	
	public void remove(int position){
		tables.remove(position);
	}
	
	public void editName(int position, Entry e){
		//Make sure only the name is changed
		e.clearOrders();
		e.orders = tables.get(position).orders;
		tables.set(position, e);
	}

	
}
