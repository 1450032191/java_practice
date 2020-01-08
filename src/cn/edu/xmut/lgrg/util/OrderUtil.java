package cn.edu.xmut.lgrg.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 高并发情况下获取订单号
 * 
 * @author liuhongbing
 *
 */
public final class OrderUtil {

private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
private static final AtomicInteger atomicInteger = new AtomicInteger(1000000);

/**
 * 创建不连续的订单号
 * 
 *            数据中心编号
 * @return 唯一的、不连续订单号
 */
	public static synchronized String getOrderNoByUUID() {
	Integer uuidHashCode = UUID.randomUUID().toString().hashCode();
	if (uuidHashCode < 0) {
	uuidHashCode = uuidHashCode * (-1);
	}
	String date = simpleDateFormat.format(new Date());
	return  date + uuidHashCode;
	}

/**
 * 获取同一秒钟 生成的订单号连续
 * 
 * @param no
 *            数据中心编号
 * @return 同一秒内订单连续的编号
 */
//	public static synchronized String getOrderNoByAtomic() {
//	atomicInteger.getAndIncrement();
//	int i = atomicInteger.get();
//	String date = simpleDateFormat.format(new Date());
//	return  date + i;
//	}
	
	public static void main(String[] args) {
		String a="2";
		System.out.println(Double.parseDouble(a));
	}
}