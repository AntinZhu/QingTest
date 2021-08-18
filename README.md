# QingTest
让公司的接口调用页面化操作,以及下单-支付-课程操作页面画，既可以方便开发和测试造数据，也能很高效的测试相关用例。页面介绍：https://www.jianshu.com/p/912d0df13c29

主要分为两部分：
      1. 流程测试
        现在只支持下单-支付-课程操作几个步骤。
        
      2. 单个接口测试
        设想：保存单个接口的URL,类型（PT,PI，PB），请求人信息,各个参数的名称，默认值，可选值等信息
        
        前提：
            参数：公司现在的接口，参数使用的是protobuf，支持传json并返回json。
            接口类型：公司接口分为三种：
                        PT:登录状态下才可调用，会校验token和session.
                        PI：系统之间调用，只会校验一些与用户无关的参数，有的接口需要传入调用人。
                        PB:非登录状态下，也可调用。
            接口请求人类型：目前接口调用人类型分为三种：学生，老师，客服。
            
        2018-10-09：
            现在的版本实现思路：
                  需要提供的功能：
                        1. 提供新增接口的功能，将各个版本或已有的接口，新增到DB,方便调用。
                        2. 接口调用功能：可以在页面填写，修改，删除参数，然后调用对应接口，请求参数和返回值都会在页面显示出来。
                  
                  实现方式：将接口参数以JSON的格式来定义，可以支持对象嵌套,枚举对象，数组形式的参数类型。
                  接口对应请求参数的proto定义：
                        message GroupSubOrderInfoListRequestForStudentV2 {
                            optional string tag = 1;
                            optional int32 limit = 2;
                            optional BriefOrderInfoStatus brief_order_status = 3;
                            repeated TimeParam time_params = 4;
                            enum BriefOrderInfoStatus {
                                unpay_brief_orderinfo_status = 1;     //未付款：订单正式生成后，家长还没有付款
                                paied_brief_orderinfo_status = 2;     //已支付,成单
                                canceled_brief_orderinfo_status = 8;   //已取消：订单超时未支付自动取消，或被老师或家长手动取消。
                            }
                        }
                        
                        message TimeParam{
                            optional string date = 1;  // "2001-01-01"
                            optional int32 start_block = 2;
                            optional int32 end_block = 3;
                        }

                  生成的接口参数json：
                        [
                            {
                                "key":"tag",
                                "name":"tag",
                                "defaultValue":""
                            },
                            {
                                "key":"limit",
                                "name":"limit",
                                "defaultValue":1
                            },
                            {
                                "key":"brief_order_status",
                                "name":"订单状态",
                                "selectable":[
                                    {
                                        "name":"unpay_brief_orderinfo_status",
                                        "value":"unpay_brief_orderinfo_status"
                                    },
                                    {
                                        "name":"paied_brief_orderinfo_status",
                                        "value":"paied_brief_orderinfo_status"
                                    },
                                    {
                                        "name":"canceled_brief_orderinfo_status",
                                        "value":"canceled_brief_orderinfo_status"
                                    }
                                ],
                                "defaultValue":{
                                    "name":"canceled_brief_orderinfo_status",
                                    "value":"canceled_brief_orderinfo_status"
                                },
                                "class":"select_editable"
                            },
                            [
                              {
                                  "key":"time_params",
                                  "name":"time_params",
                                  "detail":[
                                      {
                                          "key":"date",
                                          "name":"date",
                                          "defaultValue":{
                                              "name":"2018-10-09",
                                              "value":"2018-10-09"
                                          }
                                      },
                                      {
                                          "key":"start_block",
                                          "name":"start_block",
                                          "defaultValue":{
                                              "name":"1",
                                              "value":1
                                          }
                                      },
                                      {
                                          "key":"end_block",
                                          "name":"end_block",
                                          "defaultValue":{
                                              "name":"3",
                                              "value":3
                                          }
                                      }
                                  ]
                              }
                          ]
                        ]
            
                  接口参数说明：
                        1. 最外层是一个数组[]。
                        2. 最外层数组内的每个元素都是一个对象，该对象会有以下属性：
                                          key : 实际的参数名
                                          name ：在页面显示的参数名
                                          defaultValue ：默认值
                                          selectable ：可选值，对应与protobuf中的枚举类型
                                          detail : 该属性对应于一个对象,如果detail下第一层是一个数组[]的话，说明这个参数时可以添加多个的。 
                                          class : 用于定义前端输入框的样式，现在支持一下格式：
                                                select_editable ： 下拉框选择，对应枚举类型
                                                date_editable ： 日期选择控件
                                                input_editable ： 普通输入框
                                                datetime_editable ： 日期加时间控件，暂不支持
                         3. 数组说明：除最外层的数组，其他的数据都表示该参数一个可以被添加多个的参数。                 
                                             
                   
                   
                                          
      
