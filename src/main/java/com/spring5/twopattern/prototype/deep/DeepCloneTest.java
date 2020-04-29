package com.spring5.twopattern.prototype.deep;

/**
 * @author yinxf
 * @Date 2020/4/9
 * @Description
 **/
public class DeepCloneTest {
    public static void main(String[] args) {
        QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
        try {
            QiTianDaSheng clone = (QiTianDaSheng) qiTianDaSheng.clone();
            System.out.println("深克隆："+(qiTianDaSheng.jinGuBang == clone.jinGuBang));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        QiTianDaSheng q = new QiTianDaSheng();
        QiTianDaSheng h = q.shallowClone(q);
        System.out.println("浅克隆："+(q.jinGuBang == h.jinGuBang));
    }
}
