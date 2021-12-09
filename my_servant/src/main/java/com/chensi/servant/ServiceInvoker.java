package com.chensi.servant;

import com.chensi.service.Service;
import java.util.ServiceLoader;

/***********************************
 * @author chensi
 * @date 2021/12/9 10:07
 ***********************************/
public class ServiceInvoker {
    public static void main(String[] args) {
        //这里使用的是new创建对象的方式
        //Service service=new SimpleService();
        //service.show();
        ServiceLoader<Service> serviceLoader = ServiceLoader.load(Service.class);
        for (Service service : serviceLoader) {
            service.show();
        }
    }
}
