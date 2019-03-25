package br.com.saulo.user.ultil;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public class GenericConvert {
	
	
    public static <E, T> E convertModelMapper(T source, Class<E> typeDestination, MatchingStrategy strategy) {
        E model = null;
        if (source != null && typeDestination != null) {

             ModelMapper modelMapper = new ModelMapper();

             modelMapper.getConfiguration().setMatchingStrategy(strategy);
             model = modelMapper.map(source, typeDestination);
        }

        return model;
   }

   public static <E, T> E convertModelMapper(T source, Class<E> typeDestination) {
        return convertModelMapper(source, typeDestination, MatchingStrategies.STRICT);
   }
    
   public static <E, T> List<E> convertModelMapper(List<T> source, Type destinationType) {
        return convertModelMapper(source, destinationType, MatchingStrategies.STRICT);
   }
   
   public static <E, T> List<E> convertModelMapper(List<T> source, Type destinationType, MatchingStrategy strategy) {

       List<E> model = null;
       if (source != null && destinationType != null) {

            ModelMapper modelMapper = new ModelMapper();

            modelMapper.getConfiguration().setMatchingStrategy(strategy);
            model = modelMapper.map(source, destinationType);
       }

       return model;
  }
    
    public static <T, E> PageUser convertModelMapperToPageLoja(Page<T> page, Type typeDestination) {

        if (page != null && typeDestination != null) {

             List<E> arrayList = convertModelMapper(page.getContent(), typeDestination);
             Pageable pageable = new PageRequest(page.getNumber(), page.getSize());
             Page<E> pageResponse = new PageImpl<>(arrayList, pageable, page.getTotalElements());
             return buildPageLoja(pageResponse);
        }

        return null;
   }
    
    public static PageUser buildPageLoja(Page p) {

        if (p == null) {

             return null;
        } else {

        	 PageUser pageApi = new PageUser();
             pageApi.setContent(p.getContent());
             pageApi.setHasContent(p.hasContent());
             pageApi.setNumber(p.getNumber());
             pageApi.setNumberOfElements(p.getNumberOfElements());
             pageApi.setSize(p.getSize());
             pageApi.setTotalElements(p.getTotalElements());
             pageApi.setTotalPages(p.getTotalPages());
             pageApi.setHasNextPage(p.hasNext());
             pageApi.setHasPreviousPage(p.hasPrevious());
             pageApi.setFirst(p.isFirst());
             pageApi.setLast(p.isLast());
             if (p.previousPageable() != null) {
                  pageApi.setPreviousPage(p.previousPageable().getPageNumber());
             }
             if (p.nextPageable() != null) {
                  pageApi.setNextPage(p.nextPageable().getPageNumber());
             }
             return pageApi;
        }
   }



}
