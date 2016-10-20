package com.cme.mm.rxandroiddemo.aes;


public class Test {

	public static void main(String[] args) {

		String content = "{\"result\": {\"code\": \"200\" },\"goods_info\": {\"goods_id\": \"7\", \"user_id\": \"4\",\"start_province\": \"北京市\",\"start_city\": \"北京市\", \"start_area\": \"东城区\"";
   
		String skey = "123456879";
		try {
			//加密
			byte[] encryptResultStr = BackAES.encrypt(content, skey, 0);

			System.out.println("方法-加密后："+new String(encryptResultStr));
			String decryptString=BackAES.decrypt(new String(encryptResultStr),
					skey, 0);
			System.out.println("方法-解密后："+decryptString);
			
			/**
			 * String parseByte2HexStr(byte buf[]) //**将二进制转换成16进制
			 *byte[] parseHexStr2Byte(String hexStr) //java将16进制转换为二进制
			 */
			
			
//			String skey2 = "admin";
//			byte[] encryptResultStr2 = BackAES.newencrypt(content, skey2);
//		
//			//System.out.println("方法二加密后："+new String(encryptResultStr2));
//			////.java将2进制数据转换成16进制parseHexStr2Byte
//			String toByteString=BackAES.parseByte2HexStr(encryptResultStr2);//java将16进制转换为二进制
//			System.out.println("方法二加密后转成16进制："+toByteString);
//			
//			
//			// /**将16进制转换成2进制
//
//			byte[] ascToByte=BackAES.parseHexStr2Byte(toByteString);
//			System.out.println("方法二解密密后转成二进制："+new String(ascToByte));
//			
//			byte[] decryptString2=BackAES.newdecrypt(ascToByte,skey2);
//			System.out.println("方法二解密后："+new String(decryptString2));
//			
		
			
			
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		// String result1 = Base64.encode(encryptResultStr, chartCode);
		// System.out.println("Base64转码后：" + result1);
		//
		// System.out.println("============================================");
		// String result2 = Base64.decode(result1, chartCode);
		// System.out.println("Base64解码后：" + result2);

	}
}
