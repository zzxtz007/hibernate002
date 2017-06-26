package hibernate_002;
//学生类  

import java.sql.Blob;
import java.util.Date;

public class students
{
	// 1.公有的类
	// 2.提供公有的不带参数的默认的构造方法
	// 3.属性私有
	// 4.属性setter/getter封装

	private int sid;// 学号
	private String name;// 姓名
	private String gender;// 性别
	private Date birthday;// 出生日期
	private String adress;// 地址
	private Blob picture;//照片

	public Blob getPicture()
	{
		return picture;
	}

	public void setPicture(Blob picture)
	{
		this.picture = picture;
	}

	public students()
	{

	}

	public students(int sid, String name, String gender, Date birthday, String adress)
	{
		// super();
		this.sid = sid;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.adress = adress;
	}

	public int getSid()
	{
		return sid;
	}

	public void setSid(int sid)
	{
		this.sid = sid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getGender()
	{
		return gender;
	}

	@Override
	public String toString()
	{
		return "students [sid=" + sid + ", name=" + name + ", gender=" + gender + ", birthday=" + birthday + ", adress="
				+ adress + "]";
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public String getAdress()
	{
		return adress;
	}

	public void setAdress(String adress)
	{
		this.adress = adress;
	}

}
