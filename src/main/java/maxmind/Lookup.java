package maxmind;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.fasterxml.jackson.databind.JsonNode;
import com.maxmind.db.Reader;

public class Lookup {
    public static void main(String[] args) throws Exception {
        File mmdbFile;
        Reader reader;
        try {
            mmdbFile = new File("resources/Country.mmdb");
            reader = new Reader(mmdbFile);
            // 解析 IP 地址
            InetAddress address = InetAddress.getByName(args[0]);
            // 查询
            JsonNode response = reader.get(address);
            reader.close();
            System.out.println(response);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("用法：java -jar Lookup.jar <IP地址>");
        } catch (FileNotFoundException e) {
            System.out.println("Country.mmdb 文件不存在");
        } catch (UnknownHostException e) {
            System.out.println("IP地址不正确");
        }
    }
}
