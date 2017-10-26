package com.java.practise;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;



public class practisMongoOp {

	/** 
         * Mongo类代表与MongoDB服务器的连接，有多种构造函数。无参构造函数默认连接localhost:27017. 
         */  
        static Mongo connection = new Mongo("127.0.0.1",27017);
        /** 
         * DB类代表数据库，如果当前服务器上没有该数据库，会默认创建一个 
         */  
        static DB db = connection.getDB("dummy");  
	 
	public static void main(String[] args){  
        
        
        /** 
         * DBCollection代表集合，如果数据库中没有该集合，会默认创建一个 
         */  
        
        /** 
         * DBObject代表文档，这是一个接口，java中提供了多种实现，最简单的就是BasicDBObject了 
         */  
		AA a=new practisMongoOp().new AA();
		//a.testInsertAndQuery();
		a.testUpdate();
		
    }  
	  class AA{
		  public void testInsertAndQuery(){
			  DBCollection users = db.getCollection("users");  
		        /*DBObject user = new BasicDBObject();  
		        user.put("name", "jimmy");  
		        user.put("age", "34");  
		        DBObject address = new BasicDBObject();  
		        address.put("city", "bj");  
		        address.put("street", "bq road");  
		        address.put("mail", "ufpark 68#");  
		         //对于内嵌文档，我们需要先将内嵌文档填充后，再填充到外层文档中！  
		        user.put("address", address);  */
		        BasicDBObject user1=new BasicDBObject("name","zj").append("lover","wj").append("age","24").append("address",new BasicDBObject("street","chaoyangzhonglu").append("number", "666"));
		        // 将该文档插入到集合中  
		        users.insert(user1);  
		       // users.insert(new DBObject[] {user1});
		        // 从集合中查询数据，我们就查询一条，调用findOne即可  
		        DBObject dbUser = users.findOne();  
		        System.out.println("name" + " : "  + dbUser.get("name") );  
		        System.out.println("age" + " : "  + dbUser.get("age") );  
		        DBObject dbAddress = (DBObject)user1.get("address");  
		        //System.out.println("city" + " : "  + dbAddress.get("city") );  
		        System.out.println("street" + " : "  + dbAddress.get("street") );  
		        System.out.println("number" + " : "  + dbAddress.get("number") ); 
		        System.out.println(dbUser);
		    } 
		  
		  public void testInsertArry(){
		  DBCollection fruitShop = db.getCollection("fruitshop");  
          /** 
           *  创建水果店文档对象 
           */  
          DBObject shop1 = new BasicDBObject();  
          shop1.put("name", "The Fruit King");  
          /** 
           *  水果店内水果保存在一个内嵌文档的数组中，格式为： 
           *  [{"name" : "apple", "quality" : "good", "price" : "5.6"},  
           *   {"name" : "orange", "quality" : "normal", "price" : "1.5"}, 
           *   ......] 
           */  
          // 数组通过List表示  
          List<DBObject> fruits = new ArrayList<DBObject>();  
          // 数组中的每一个文档，我们通过BasicDBObjectBuilder来构造  
          fruits.add(BasicDBObjectBuilder.start().add("name", "apple").add("quality", "good").add("price", "5.6").get());  
          fruits.add(BasicDBObjectBuilder.start().add("name", "orange").add("quality", "normal").add("price", "1.5").get());  
          shop1.put("fruits", fruits);  
            
          fruitShop.insert(shop1);  
 
	  }
		  public void testDelete(){
		  DBCollection users=db.getCollection("users");
		  DBObject removecondition=new BasicDBObject();
		  removecondition.put("name","zj"); 
		  System.out.println(removecondition);
		  users.remove(removecondition);
	  }
		  //db.movie.update({name:'zj'},{$set:{lover:"issue"}})满足条件的只更新一条，不排序即为最早一条记录
		  public void testUpdate(){
			  DBCollection users=db.getCollection("users");
			  DBObject queryconditon=new BasicDBObject();
			  queryconditon.put("name","zj"); 
			  DBObject setinner=new BasicDBObject();
			  setinner.put("lover","wj");
			  DBObject setcondition=new BasicDBObject();
			  setcondition.put("$set",setinner);
			  users.update(queryconditon, setcondition); 
			  //users.updateMulti(queryconditon, setcondition);  更新所有
			  
		  }
	  }
  
}
