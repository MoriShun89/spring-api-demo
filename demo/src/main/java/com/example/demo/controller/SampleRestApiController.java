package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/sample/api")
public class SampleRestApiController {
  
  private static final Logger log = LoggerFactory.getLogger( SampleRestApiController.class );

  // パスパラメータ
  /*
   * /sample/api/test/{param}にGETリクエストを送信することで、paramに指定した値をパスパラメータとして受け取ることができます。
   * 例えば、/sample/api/test/fooにGETリクエストを送信すると、サーバー側ではtestPathVariable()メソッドが呼び出され、
   * 引数paramに"foo"が渡されます。このメソッドは、ログに"foo"を出力し、"受け取ったパラメータ：foo"という文字列を返します。
   */
  @RequestMapping("/test/{param}")
  private String testPathVariable( @PathVariable("param") String param ) {
    log.info( param );
    return "受け取ったパラメータ：" + param;
  }

  // リクエストパラメータ
  /*
   * /sample/api/test?param=valueにGETリクエストを送信することで、paramに指定した値をリクエストパラメータとして受け取ることができます。
   * 例えば、/sample/api/test?param=barにGETリクエストを送信すると、サーバー側ではtestRequestParam()メソッドが呼び出され、
   * 引数paramに"bar"が渡されます。このメソッドは、ログに"bar"を出力し、"受け取ったパラメータ：bar"という文字列を返します。
   */
  @RequestMapping("/test")
  private String testRequestParam( @RequestParam("param") String param ) {
    log.info( param );
    return "受け取ったパラメータ：" + param;
  }

  // リクエストボディ
  /*
   * /sample/api/testにPOSTリクエストを送信することで、リクエストボディに指定した文字列を受け取ることができます。
   * 例えば、以下のようなJSON形式の文字列をリクエストボディに指定して、/sample/api/testにPOSTリクエストを送信すると、
   * サーバー側ではtestRequestBody()メソッドが呼び出され、引数bodyに{"name":"Alice","age":20}が渡されます。
   * このメソッドは、ログに{"name":"Alice","age":20}を出力し、"受け取ったリクエストボディ：{"name":"Alice","age":20}"という文字列を返します。
   */
  @RequestMapping(value = "/test", method = RequestMethod.POST)
  private String testRequestBody( @RequestBody String body ) {
    log.info( body );
    return "受け取ったリクエストボディ：" + body;
  }

  // エラーをthrow
  @RequestMapping("err/ex")
  public String testException() throws Exception {

    throw new RuntimeException( "エラー発生" );
  }
}
