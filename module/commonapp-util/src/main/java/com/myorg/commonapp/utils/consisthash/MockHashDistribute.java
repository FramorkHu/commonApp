package com.myorg.commonapp.utils.consisthash;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yanshan.hy on 2021/4/6
 */
public class MockHashDistribute {

    public static void main(String[] args) {

        String nameStrs = "咏君,菁喜,刘艾";
        List<String> nameList = Arrays.asList(nameStrs.split(","));


        List<String> ipList = new ArrayList<>();
        ipList.add("11.36.76.186");
        ipList.add("11.144.54.79");
        ipList.add("11.34.28.32");
        ipList.add("11.145.26.99");
        ipList.add("11.190.206.102");
        ipList.add("11.233.76.198");
        ipList.add("11.188.153.69");
        ipList.add("11.233.72.100");



        IHashService iHashService = new HashService();
        List<Node<String>> nodes = new ArrayList<Node<String>>();
        ipList.forEach(name->{
            Node<String> node = new Node<String>(name, name);
            nodes.add(node);
        });

        ConsistentHash consistentHash = new ConsistentHash(iHashService, 500, nodes);


        ListMultimap<String,String> nameIp = LinkedListMultimap.create();
        nameList.forEach(name->{
            Node<String> node = consistentHash.get(name);

            nameIp.put(node.getIp(),name );
        });

        System.out.println(nameIp);

    }
}
