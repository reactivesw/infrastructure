package io.reactivesw.infrastructure.application.model;

import lombok.Data;

import java.util.List;

/**
 * The result of pagedQuery.
 */
@Data
public class PagedQueryResult<T> {

  /**
   * The offset
   */
  private Integer offset;
  /**
   * The count number.
   */
  private Integer count;
  /**
   * Total number of result.
   */
  private Integer total;
  /**
   * Result list.
   */
  private List<T> results;
  /**
   * The facet.
   */
  private Object facet;
}
