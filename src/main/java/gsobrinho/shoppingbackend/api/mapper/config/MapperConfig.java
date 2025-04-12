package gsobrinho.shoppingbackend.api.mapper.config;

import gsobrinho.shoppingbackend.api.mapper.DepartmentMapper;
import gsobrinho.shoppingbackend.api.mapper.ProductMapper;
import gsobrinho.shoppingbackend.domain.model.Department;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ProductMapper productMapper(){
        return Mappers.getMapper(ProductMapper.class);
    }

    @Bean
    public DepartmentMapper departmentMapper(){
        return Mappers.getMapper(DepartmentMapper.class);
    }
}
