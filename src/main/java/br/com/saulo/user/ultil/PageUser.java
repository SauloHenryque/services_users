
package br.com.saulo.user.ultil;

import java.util.List;

import org.junit.Ignore;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Ignore
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageUser<T>{

     @JsonInclude(Include.ALWAYS)
     public int number;

     @JsonInclude(Include.ALWAYS)
     public int size;

     @JsonInclude(Include.ALWAYS)
     public int totalPages;

     @JsonInclude(Include.ALWAYS)
     public int numberOfElements;

     @JsonInclude(Include.ALWAYS)
     public long totalElements;

     @JsonInclude(Include.ALWAYS)
     public boolean firstPage;

     @JsonInclude(Include.ALWAYS)
     public boolean hasPreviousPage;

     @JsonInclude(Include.ALWAYS)
     public boolean hasNextPage;

     @JsonInclude(Include.ALWAYS)
     public boolean hasContent;

     @JsonInclude(Include.ALWAYS)
     public boolean first;

     @JsonInclude(Include.ALWAYS)
     public boolean last;

     @JsonInclude(Include.ALWAYS)
     public int nextPage;

     @JsonInclude(Include.ALWAYS)
     public int previousPage;

     public List<T> content;
}
