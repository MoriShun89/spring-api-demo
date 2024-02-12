package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("http-method/api")
public class HttpMethodApiController {

  /** 登録：CRUDでいう <b>C:CREATE</b> を行うAPI */
@RequestMapping(value="/resource", method=RequestMethod.POST)
private String create(@RequestBody String data) {
    return "登録だよ";
}
/** 参照：CRUDでいう <b>R:READ</b> を行うAPI */
@RequestMapping(value="/resource/{id}", method=RequestMethod.GET)
private String read(@PathVariable String id) {
    return "参照だよ";
}
/** 削除：CRUDでいう <b>D:DELETE</b> を行うAPI */
@RequestMapping(value="/resource/{id}", method=RequestMethod.DELETE)
private String delete(@PathVariable String id) {
    return "削除だよ";
}

/** 更新：CRUDでいう <b>U:UPDATE</b> を行うAPI */
@RequestMapping(value="/resource/{id}", method=RequestMethod.PUT)
private String update(@PathVariable String id, @RequestBody String data) {
    return "更新だよ";
}
}
