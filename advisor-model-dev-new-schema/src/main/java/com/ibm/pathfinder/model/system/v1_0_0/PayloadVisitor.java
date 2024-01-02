/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */

package com.ibm.pathfinder.model.system.v1_0_0;

public interface PayloadVisitor<T> {
  public T visit(Entity entity);

  public T visit(Relationship relationship);
}
