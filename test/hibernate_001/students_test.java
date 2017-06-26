package hibernate_001;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hibernate_002.students;

public class students_test
{
	private SessionFactory sessionfactory;
	private Session session;
	private Transaction transaction;

	@Before
	public void init()
	{
		// 创建配置对象
		Configuration config = new Configuration().configure();

		// 创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();

		// 创建会话工厂对象
		sessionfactory = config.buildSessionFactory(serviceRegistry);

		// 会话对象
		session = sessionfactory.openSession();

		// 开启事务
		transaction = session.beginTransaction();
	}

	 @Test
	 public void testSaveStudents()
	 {
	 // 生成学生对象
	 students s = new students(3, "张三丰", "男", new Date(), "武当山");
	 session.save(s);
	
	 }

	@Test
	public void testWriteBlob() throws IOException
	{
		students s = new students(3, "张三丰", "男", new Date(), "武当山");
		// 获取照片文件
		File f = new File("D:" + File.separator + "Girl.JPG");
		// 获得文件的输入流
		InputStream inputStream = new FileInputStream(f);
		// 创建一个blob对象
		Blob b = Hibernate.getLobCreator(session).createBlob(inputStream, inputStream.available());
		s.setPicture(b);
		session.save(s);
	}

	@Test
	public void testReadBlob() throws SQLException, IOException
	{
		students s = (students) session.get(students.class, 3);
		//获得blob对象
		Blob image = s.getPicture();
		//获得输入流
		InputStream inputStream = image.getBinaryStream();
		//创建输出流
		File f = new File("D:"+File.separator+"bb.jpg");
		//获得输出流
		OutputStream outputStream =	new FileOutputStream(f);
		//创建缓冲区
		byte[] buff = new byte[inputStream.available()];
		inputStream.read(buff);
		outputStream.write(buff);
	}

	@After
	public void destory()
	{
		transaction.commit();// 提交事务
		session.close();// 关闭会话
		sessionfactory.close();// 关闭会话工厂
	}
}
