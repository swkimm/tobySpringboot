package tobyspring.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> autoConfigs = new ArrayList<>();

//        for(String candidate : ImportCandidates.load(MyAutoConfigImportSelector.class, classLoader)) {
//            autoConfigs.add(candidate);
//        }
//
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);

        return autoConfigs.toArray(new String[0]);
    }
}
