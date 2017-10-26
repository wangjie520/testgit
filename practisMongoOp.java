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
         * Mongo�������MongoDB�����������ӣ��ж��ֹ��캯�����޲ι��캯��Ĭ������localhost:27017. 
         */  
        static Mongo connection = new Mongo("127.0.0.1",27017);
        /** 
         * DB��������ݿ⣬�����ǰ��������û�и����ݿ⣬��Ĭ�ϴ���һ�� 
         */  
        static DB db = connection.getDB("dummy");  
	 
	public static void main(String[] args){  
        
        
        /** 
         * DBCollection�����ϣ�������ݿ���û�иü��ϣ���Ĭ�ϴ���һ�� 
         */  
        
        /** 
         * DBObject�����ĵ�������һ���ӿڣ�java���ṩ�˶���ʵ�֣���򵥵ľ���BasicDBObject�� 
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
		         //������Ƕ�ĵ���������Ҫ�Ƚ���Ƕ�ĵ���������䵽����ĵ��У�  
		        user.put("address", address);  */
		        BasicDBObject user1=new BasicDBObject("name","zj").append("lover","wj").append("age","24").append("address",new BasicDBObject("street","chaoyangzhonglu").append("number", "666"));
		        // �����ĵ����뵽������  
		        users.insert(user1);  
		       // users.insert(new DBObject[] {user1});
		        // �Ӽ����в�ѯ���ݣ����ǾͲ�ѯһ��������findOne����  
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
           *  ����ˮ�����ĵ����� 
           */  
          DBObject shop1 = new BasicDBObject();  
          shop1.put("name", "The Fruit King");  
          /** 
           *  ˮ������ˮ��������һ����Ƕ�ĵ��������У���ʽΪ�� 
           *  [{"name" : "apple", "quality" : "good", "price" : "5.6"},  
           *   {"name" : "orange", "quality" : "normal", "price" : "1.5"}, 
           *   ......] 
           */  
          // ����ͨ��List��ʾ  
          List<DBObject> fruits = new ArrayList<DBObject>();  
          // �����е�ÿһ���ĵ�������ͨ��BasicDBObjectBuilder������  
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
		  //db.movie.update({name:'zj'},{$set:{lover:"issue"}})����������ֻ����һ����������Ϊ����һ����¼
		  public void testUpdate(){
			  DBCollection users=db.getCollection("users");
			  DBObject queryconditon=new BasicDBObject();
			  queryconditon.put("name","zj"); 
			  DBObject setinner=new BasicDBObject();
			  setinner.put("lover","wj");
			  DBObject setcondition=new BasicDBObject();
			  setcondition.put("$set",setinner);
			  users.update(queryconditon, setcondition); 
			  //users.updateMulti(queryconditon, setcondition);  ��������
			  
		  }
	  }
  
}
