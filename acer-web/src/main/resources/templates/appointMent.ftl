
<!DOCTYPE html>
<html>

  <head>
    <title>预约看房—Q房网</title>
    <meta name="keywords" content="预约看房,在线报名,看房团,Q房网"/>
    <meta name="description" content="${cityName}Q房网在线报名,预约看房。${cityName}Q房网为广大用户搭建沟通、预约看房平台,您可以报名参加感兴趣的楼盘看房团,省时省力; 同时Q房网会及时关注您的预约看房申请,为广大网友精心规划优质看房路线,方便大家省时省力选出最合适自己的房子。" />
    <link type="text/css" rel="stylesheet" href="<#if cdn??>${cdn}<#else>${styles}</#if>/css/appointment.css" />
  </head>
  
  <body>
    <header class="header-body fixed">
      <div class="header">
        <h1 class="title">预约看房</h1>
      </div>
      <a class="back js-back" href="javascript:;"></a>
    </header>
	    
   <section class="progress-container">
    <div class="items green js-time">
      <i class="icon iconfont icon-yuyueshijian"></i>
      <span class="txt">预约时间</span>
    </div>
    <div class="items js-phone">
      <i class="icon iconfont icon-appxiazai1"></i>
      <span class="txt js-phoneTxt">
      <#if user??>
      	确认信息
      <#else>
      	验证手机
      </#if>
      </span>
    </div>
    <div class="items js-complete">
      <i class="icon iconfont icon-wanchengchenggong"></i>
      <span class="txt js-completeTxt">完成</span>
    </div>
    <div class="progress line-one"></div>
    <div class="progress line-two"></div>
   </section>
        
   <div class="js-first">
    <section class="select-date" id="select-date">
     <div class="title">选择看房日期</div>
     <div class="slide-body" js-plugin="slide">
      <div class="slide">
       <a class="box-align items checked" data-day="anytime" data-date="anytime" href="javascript:void(0);">
        <div>
          <span class="anytime">随时</span>
          <span class="anytime">看房</span>
        </div>
       </a>
       <#if appointMentDates?? && appointMentDates?size gt 0>
      	<#list appointMentDates as date>
	      <a class="items" data-day="${date['fullCurrentDate']}" data-date="${date['fullCurrentDate']} ${date['weekDay']}" href="javascript:;">
           <span class="week">${date['weekDay']}</span>
           <span class="date">${date['simpleCurrentDate']}</span>
          </a>
         </#list>
        </#if>
      </div>
     </div>
    </section>
    
   <section class="select-time" id="select-time">
    <div class="title">看房时间</div>
    <div class="content">
      <#if lookTimes?? && lookTimes?size gt 0>
    	<#list lookTimes as time>
    	    <#assign hour = ''>
    	    <#if time == 'DEFAULT'>
    	    	<#assign hour = 'DEFAULT'>
    	    <#elseif time == 'MORNING'>
    	    	<#assign hour = '8'>
    	    <#elseif time == 'NOON'>
    	    	<#assign hour = '12'>
    	    <#elseif time == 'AFTERNOON'>
    	    	<#assign hour = '14'>
    	    <#elseif time == 'NIGHT'>
    	    	<#assign hour = '18'>
    	    </#if>
    	    
    	    <a class="items time ${time}<#if time_index == 0> checked</#if>"
    		href="javascript:void(0);"
    		data-time="${time}"
    		data-hour="${hour}"
    		>
    		${time.desc!}<#if time.timeQuantum?? && time.timeQuantum != ''>(${time.timeQuantum})</#if>
    		</a>
    	</#list>
      </#if>
    </div>
   </section>
   
   
    <section class="take-look-content">
    <div class="title">带看人</div>
    <a class="trigger" id="select-broker" href="jabascript:;">
      <span>自动分配经纪人</span>
      <i class="arrow"></i>
    </a>
  </section>
   
   <section class="form-body">
     <a class="submit js-first-step activate" id="ga-appointment-first" href="javascript:;">下一步</a>
   </section>
  </div>
    
   <div class="js-second" style="display:none;">
    <section class="form-body">
	    <div class="text-items">
	      <input class="text username" type="text" placeholder="请输入您的称呼" value="" maxLength="20">
	      <a class="clear-text" href="javascript:;"></a>
	    </div>
	    <div class="text-items">
	      <input class="text phone" type="tel" placeholder="请输入您的手机号"
	      <#if user??>
	       <#assign start = user.phone?substring(0, 3)>
	       <#assign end = user.phone?substring(7, 11)>
	      value="${start + '****' + end}"
	      readOnly="readOnly"
	      </#if>
	      maxLength="11"/>
	      <#if !(user??)>
	      <a class="clear-text" href="javascript:;"></a>
	      </#if> 
	    </div>
    </section>
    
  
    
    
    <section class="form-body">
      <a class="submit js-second-step disabled" id="ga-appointment-second" href="javascript:void(0);">下一步</a>
    </section>
    </div>
	
  <#-- 消息提示-->
  <section class="layer-tip popup-layer" style="display:none">
   <div class="tip-body"> 
    <span class="info">验证码错误</span> 
   </div> 
  </section>
    
   <#-- 未登录用户短信验证 -->
   <div class="js-third" style="display:none;">
   <section class="note-container form-body">
    <div class="tip js-sent-phone">短信验证码已发送至</div>
    <div class="text-items">
        <input class="text smscode" type="text" placeholder="短信验证码" maxLength="6">
        <a class="clear-text" href="javascript:;"></a>
    </div>
    <div class="no-received">
        <span class="txt"></span>
        <a class="send js-resend" href="javascript:;">重新发送</a>
    </div>
   </section>
   
   <section class="form-body">
      <a class="submit disabled js-completed" id="ga-appointment-completed" href="javascript:void(0);">完成</a>
    </section>
   </div>
   
  <section class='picture-identifying js-layerValidate hide'>
    <div class='body'>
      <div class='header'>
        <div class='title'>
          <span>找出</span>
          <span class='orange'>[倒着]</span>
          <span>的图片</span>
        </div>
        <a class='reload js-reload' href="javascript:;">换一组</a>
      </div>
      <div class='content js-piccontent'></div>
      <div class='footer'>
        <a class='cancel js-captcha-cancel' href="javascript:;">取消</a>
        <a class='ok js-captcha-ok' href="javascript:;">确认</a>
      </div>
    </div>
  </section>
	
    <section class="complete-container js-result" style="display:none;">
      <i class="icon iconfont icon-tell"></i>
      <div class="info">预约成功</div>
      <div class="tip">工作人员将会尽快与您联系，请保持手机畅通</div>
      <div class="btn-box">
       <a class="btn green js-goon" href="javascript:;" data-url="${ctx2}/${bizType}/${roomId}">继续看房</a>
       <a class="btn js-myAppoint" href="javascript:;">查看预约</a>
      </div>
    </section>
      <!--选择经济人-->
    <input id="personid" type="hidden" value='<#if personId??>${personId}</#if>'/>
  <section class="select-broker hide" id="select-broker-dialog">
    <header class="header-body">
      <div class="header">
        <h1 class="title">选择经纪人</h1>
      </div>
      <a class="back" href="javascript:;"></a>
    </header>
    <div class="slide-body" js-plugin="slide" data-direction="vertical">
      <div class="slide">
        <!-- 自动分配 -->
        <div class="automatically-assigned js-choose">
          <div class="photo">
            <img src="/qfang-wap/themes/default/images/frontend/photo-dft.png" />
          </div>
          <div class="text">
            <div class="name">自动分配经纪人</div>
            <div class="description">为您自动分配最了解房源的经纪人</div>
          </div>
        	  
          <div class="mock-checkbox checked"><i class="icon iconfont icon-wanchengchenggong"></i></div>
        </div>
        <!-- end 自动分配 -->
        <div class="divider-layer">
          <div class="text">您也可以手动选择以下经纪人</div>
        </div>
       
        <#if personList?? && personList?size gt 0>
      	<#list personList as person>
      	 <div class="choose-broker-list">
          <div class="choose-broker js-choose">
          <input id='personid' type='hidden' value='${person.id}'/>
            <div class="photo">
              <img src="../static/img/photo-placeholder.png" data-src="${person.pictureUrl}"
                lazyload> </div>
            <div class="text">
              <div class="name">${person.name}</div>
              <div class="description"><#if person.erpScoreLevel??>${person.erpScoreLevel.desc}<#else>资深店经理</#if></div>
            </div>
            
            <div class="mock-checkbox">
              <i class="icon iconfont icon-wanchengchenggong"></i>
            </div>
          </div>
          <div class="dsp"><#if person.o2oPersonRole??>${person.o2oPersonRole.rentDesc}<#else>由我上门实拍房源图，展示房源细节</#if></div>
        </div>
         </#list>
        </#if>
        </div>
        </div>
        <div class="btn-sure">
      <a class="alink js-selected" href="javascript:;">确定</a>
    </div>
  </section>
	
    <section class="app-download" style="display:none;">
     <div class="logo">
      <img src="<#if cdn??>${cdn}/static/img<#else>${images}/img-new</#if>/app-logo.png" />
     </div>
     <div class="txt-box">
      <p class="tit">使用Q房网APP</p>
      <p class="desc">买房更快更省心</p>
     </div>
     <a class="download js-dl-app" id="ga-detail-dl" href="javascript:;">立即打开</a>
     <a class="close" href="javascript:;"></a>
    </section>
  
  </body>
  <script type="text/javascript">
    var app = {
    	ctx:'${ctx2}',
    	id: '${roomId}',
    	bizType: '${bizType}',
    	<#if user??>
    	userId:'${user.id?c}',
    	phone:'${user.phone}',
    	</#if>
    	<#if appointCount??>
    	count:'${appointCount}',
    	</#if>
    	<#if personId??>
    	personId: '${personId}',
    	</#if>
    };
  </script>
  <script>
    try {
        window.addEventListener('pagehide', function(e) {
            var $body = $(document.body);
            $body.children().remove();
			
            setTimeout(function() {
                $body.append("<script type='text/javascript'>window.location.reload();<\/script>");
            });
        });
    }catch(e){
    }
  </script>
  
  <#if cdn??>
    <script type="text/javascript" src="${cdn}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${cdn}/js/jquery.qfang.min.js"></script>
  </#if>
  <script type="text/javascript" src="${scripts}/plugin/storage/lscache.min.js"></script>
  <script type="text/javascript" src="${scripts}/frontend/appointment/appointment.js"></script>
  <script type="text/javascript" src="${scripts}/frontend/footer.js"></script>

</html>