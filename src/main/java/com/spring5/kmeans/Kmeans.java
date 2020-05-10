package com.spring5.kmeans;

import java.util.ArrayList;
import java.util.Random;


public class Kmeans {
    private int numOfCluster;// 分成多少簇
    private int timeOfIteration;// 迭代次数
    private int dataSetLength;// 数据集元素个数，即数据集的长度
    private ArrayList<float[]> dataSet;// 数据集链表
    private ArrayList<float[]> center;// 中心链表
    private ArrayList<ArrayList<float[]>> cluster; //簇
    private ArrayList<Float> sumOfErrorSquare;// 误差平方和
    private Random random;

    /**
     * 设置需分组的原始数据集
     *
     * @param dataSet
     */

    public void setDataSet(ArrayList<float[]> dataSet) {
        this.dataSet = dataSet;
    }

    /**
     * 获取结果分组
     *
     * @return 结果集
     */

    public ArrayList<ArrayList<float[]>> getCluster() {
        return cluster;
    }

    /**
     * 构造函数，传入需要分成的簇数量
     *
     * @param numOfCluster
     *    簇数量,若numOfCluster<=0时，设置为1，若numOfCluster大于数据源的长度时，置为数据源的长度
     */
    public Kmeans(int numOfCluster) {
        if (numOfCluster <= 0) {
            numOfCluster = 1;
        }
        this.numOfCluster = numOfCluster;
    }

    /**
     * 初始化
     */
    private void init() {
        timeOfIteration = 0;
        random = new Random();
        dataSetLength = dataSet.size();
        center = initCenters();
        cluster = initCluster();
        sumOfErrorSquare = new ArrayList<Float>();
    }

    /**
     * 初始化中心数据链表，分成多少簇就有多少个中心点
     *
     * @return 中心点集
     */
    private ArrayList<float[]> initCenters() {
        ArrayList<float[]> center = new ArrayList<float[]>();
        int[] randoms = new int[numOfCluster];
        boolean flag;
        int temp = random.nextInt(dataSetLength);
        randoms[0] = temp;
        //randoms数组中存放数据集的不同的下标
        for (int i = 1; i < numOfCluster; i++) {
            flag = true;
            while (flag) {
                temp = random.nextInt(dataSetLength);

                int j=0;
                for(j=0; j<i; j++){
                    if(randoms[j] == temp){
                        break;
                    }
                }

                if (j == i) {
                    flag = false;
                }
            }
            randoms[i] = temp;
        }

        //把randoms中三个下标对应的点，放到中心链表中。
        for (int i = 0; i < numOfCluster; i++) {
            center.add(dataSet.get(randoms[i]));// 生成初始化中心链表
            System.out.println("初始化中心点"+i+":"+dataSet.get(randoms[i])[0] +","+ dataSet.get(randoms[i])[1]);
        }
        return center;
    }

    /**
     * 初始化簇集合
     *
     * @return 一个分为k簇的空数据的簇集合
     */
    private ArrayList<ArrayList<float[]>> initCluster() {
        ArrayList<ArrayList<float[]>> cluster = new ArrayList<ArrayList<float[]>>();
        for (int i = 0; i < numOfCluster; i++) {
            cluster.add(new ArrayList<float[]>());
        }

        return cluster;
    }

    /**
     * 计算两个点之间的距离
     *
     * @param element
     *            点1
     * @param center
     *            点2
     * @return 距离
     */
    private float distance(float[] element, float[] center) {
        float distance = 0.0f;
        float x = element[0] - center[0];
        float y = element[1] - center[1];
        float z = x * x + y * y;
        distance = (float) Math.sqrt(z);

        return distance;
    }

    /**
     * 获取距离集合中最小距离的位置
     *
     * @param distance
     *            距离数组
     * @return 最小距离在距离数组中的位置
     */
    private int minDistance(float[] distance) {
        float minDistance = distance[0];
        int minLocation = 0;
        for (int i = 1; i < distance.length; i++) {
            if (distance[i] <= minDistance) {
                minDistance = distance[i];
                minLocation = i;
            }
        }
        return minLocation;
    }

    /**
     * 核心，将当前元素放到最小距离中心相关的簇中
     */
    private void clusterSet() {
        float[] distance = new float[numOfCluster];
        for (int i = 0; i < dataSetLength; i++) {
            for (int j = 0; j < numOfCluster; j++) {
                //计算对象到中心点的距离
                distance[j] = distance(dataSet.get(i), center.get(j));
            }
            //获取最小距离的点
            int minLocation = minDistance(distance);
            //将遍历的对象点放到距离最近的中心点集合中
            cluster.get(minLocation).add(dataSet.get(i));// 核心，将当前元素放到最小距离中心相关的簇中

        }
    }

    /**
     * 求两点误差平方的方法
     *
     * @param element
     *            点1
     * @param center
     *            点2
     * @return 误差平方
     */
    private float errorSquare(float[] element, float[] center) {
        float x = element[0] - center[0];
        float y = element[1] - center[1];

        float errSquare = x * x + y * y;

        return errSquare;
    }

    /**
     * 计算误差平方和准则函数方法
     */
    private void countRule() {
        float jcF = 0;
        for (int i = 0; i < cluster.size(); i++) {
            for (int j = 0; j < cluster.get(i).size(); j++) {
                jcF += errorSquare(cluster.get(i).get(j), center.get(i));
            }
        }
        sumOfErrorSquare.add(jcF); //记录误差值
    }

    /**
     * 设置新的簇中心方法
     */
    private void setNewCenter() {
        for (int i = 0; i < numOfCluster; i++) {
            int n = cluster.get(i).size();
            if (n != 0) {
                float[] newCenter = { 0, 0 };
                for (int j = 0; j < n; j++) {
                    newCenter[0] += cluster.get(i).get(j)[0];
                    newCenter[1] += cluster.get(i).get(j)[1];
                }
                // 设置一个平均值，把x的坐标加起来，Y的坐标加起来，求平均值，把平均值对应的点设置到新的中心集合中。
                newCenter[0] = newCenter[0] / n;
                newCenter[1] = newCenter[1] / n;
                center.set(i, newCenter);
            }
        }
    }

    /**
     * 打印数据，测试用
     *
     * @param dataArray
     *            数据集
     * @param dataArrayName
     *            数据集名称
     */
    public void printDataArray(ArrayList<float[]> dataArray,
                               String dataArrayName) {
        for (int i = 0; i < dataArray.size(); i++) {
            System.out.println("print:" + dataArrayName + "[" + i + "]={"
                    + dataArray.get(i)[0] + "," + dataArray.get(i)[1] + "}");
        }
        System.out.println("===================================");
    }

    /**
     * Kmeans算法核心过程方法
     */
    private void kmeans() {
        init();

        // 循环分组，直到误差不变为止
        while (true) {
            clusterSet();

            countRule();

            // 误差不变了，分组完成
            if (timeOfIteration != 0) {
                if (sumOfErrorSquare.get(timeOfIteration) - sumOfErrorSquare.get(timeOfIteration - 1) == 0) {
                    break;
                }
            }

            //设置新的中心点
            setNewCenter();
            timeOfIteration++;
            cluster.clear();
            cluster = initCluster();
        }

    }

    /**
     * 执行算法
     */
    public void execute() {
        long startTime = System.currentTimeMillis();
        System.out.println("kmeans begins");
        kmeans();
        long endTime = System.currentTimeMillis();
        System.out.println("kmeans running time=" + (endTime - startTime)
                + "ms");
        System.out.println("kmeans ends");
        System.out.println();
    }
}
