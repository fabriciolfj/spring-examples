package com.github.fabriciolfj.javaexamples.config;

import com.github.fabriciolfj.javaexamples.service.FileReplicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.export.assembler.InterfaceBasedMBeanInfoAssembler;
import org.springframework.jmx.export.assembler.MBeanInfoAssembler;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;
import org.springframework.jmx.support.MBeanServerFactoryBean;

import java.util.Map;

@Configuration
public class JmxConfig {

    /*@Bean
    public MBeanExporter mBeanExporter() {
        var mbean = new AnnotationMBeanExporter();
        mbean.setDefaultDomain("bean");
        return mbean;
    }*/

    /*
    @Bean
    public MBeanExporter mBeanExporter() {
        var beansToExport = Map.<String, Object>of(
                "bean:name=documentReplicator", "documentReplicator");
        var mbeanExporter = new MBeanExporter();
        mbeanExporter.setBeans(beansToExport);
        mbeanExporter.setServer(mbeanServer().getObject()); //quando tiver mais de um servidor jmx disponivel
        mbeanExporter.setAssembler(assembler()); //permite espeificar quais metodos esportar
        return mbeanExporter;
    }

    //exporta todos os metodos especificados na interface
    /*
    public MBeanInfoAssembler assembler() {
        var assembler = new InterfaceBasedMBeanInfoAssembler();
        assembler.setManagedInterfaces(FileReplicator.class);

        return assembler;
    }

    @Bean
    public MBeanInfoAssembler assembler() {
        var assembler = new MethodNameBasedMBeanInfoAssembler();
        assembler.setManagedMethods("getSrcDir", "setSrcDir", "getDestDir", "setDestDir", "replicate");
        return assembler;
    }

    @Bean
    public MBeanServerFactoryBean mbeanServer() {
        var mbeanServer = new MBeanServerFactoryBean();
        mbeanServer.setLocateExistingServerIfPossible(true);
        mbeanServer.setAgentId("pop-os_1687479617716");

        return mbeanServer;
    }*/
}


