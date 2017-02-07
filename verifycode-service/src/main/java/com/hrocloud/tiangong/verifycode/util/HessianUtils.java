package com.hrocloud.tiangong.verifycode.util;


import com.alibaba.com.caucho.hessian.io.Hessian2Input;
import com.alibaba.com.caucho.hessian.io.Hessian2Output;
import com.alibaba.dubbo.common.serialize.support.hessian.Hessian2ObjectOutput;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by hanzhihua on 2017/1/2.
 */
public abstract class HessianUtils {

    private final static Logger logger = LoggerFactory.getLogger(HessianUtils.class);

    public static byte[] encode(Object object)throws IOException{
        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        Hessian2Output h2o = new Hessian2Output(os);
        try {
            h2o.writeObject(object);
        } finally  {
            IOUtils.closeQuietly(os);
            closeQuietly(h2o);
        }
        return os.toByteArray();
    }

    public static <T> T decode(byte[] data) throws IOException{
        Object object = null;
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        Hessian2Input hessian2Input = new Hessian2Input(in);
        try{
            object = hessian2Input.readObject();
        }finally {
            IOUtils.closeQuietly(in);
            closeQuietly(hessian2Input);
        }
        return (T) object;
    }

    private static void closeQuietly(Hessian2Output hessian2Output){
        try {
            if (hessian2Output != null) {
                hessian2Output.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }

    private static void closeQuietly(Hessian2Input hessian2Input){
        try {
            if (hessian2Input != null) {
                hessian2Input.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }
}
