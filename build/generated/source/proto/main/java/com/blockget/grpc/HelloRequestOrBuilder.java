// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ExampleServices.proto

package com.blockget.grpc;

public interface HelloRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.blockget.grpc.HelloRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string firstname = 1;</code>
   */
  java.lang.String getFirstname();
  /**
   * <code>string firstname = 1;</code>
   */
  com.google.protobuf.ByteString
      getFirstnameBytes();

  /**
   * <code>string lastname = 2;</code>
   */
  java.lang.String getLastname();
  /**
   * <code>string lastname = 2;</code>
   */
  com.google.protobuf.ByteString
      getLastnameBytes();

  /**
   * <code>int64 age = 3;</code>
   */
  long getAge();
}
