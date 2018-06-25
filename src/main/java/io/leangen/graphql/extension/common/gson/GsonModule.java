package io.leangen.graphql.extension.common.gson;

import io.leangen.graphql.extension.SimpleModule;
import io.leangen.graphql.generator.mapping.OutputConverter;
import io.leangen.graphql.generator.mapping.TypeMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GsonModule implements SimpleModule {

    private static final GsonArrayAdapter arrayAdapter = new GsonArrayAdapter();

    @Override
    public List<TypeMapper> getTypeMappers() {
        return Arrays.asList(new GsonScalarTypeMapper(), arrayAdapter);
    }

    @Override
    public List<OutputConverter<?, ?>> getOutputConverters() {
        return Collections.singletonList(arrayAdapter);
    }

    @Override
    public void setUp(SetupContext context) {
        if (!getTypeMappers().isEmpty()) {
            context.getSchemaGenerator().withTypeMappersPrepended(getTypeMappers().toArray(new TypeMapper[0]));
        }
        if (!getOutputConverters().isEmpty()) {
            context.getSchemaGenerator().withOutputConvertersPrepended(getOutputConverters().toArray(new OutputConverter[0]));
        }
    }
}
