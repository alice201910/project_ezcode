package org.ezcode.demo.domain;

import lombok.Data;

/**
 * AmountVO
 */
@Data
public class AmountVO {
   private Integer total;	
   private Integer tax_free;	
   private Integer vat;	
   private Integer point;	
   private Integer discount;
}