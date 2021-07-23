void udp_read(void){
  Data_length=Udp.parsePacket();  //获取接收的数据的长度
  if(Data_length)  //如果有数据那么Data_length不为0，无数据Data_length为0
  {
    len = Udp.read(incomingPacket, 65535);  //读取数据，将数据保存在数组incomingPacket中
    if (len > 0)  //为了避免获取的数据后面乱码做的判断
    {
      incomingPacket[len] = 0;
    }
    message_udp = incomingPacket;
    uint16_t m_d = 0;
    length_a = 0;
    do
    {
      commaPosition = message_udp.indexOf(',');//检测字符串中的逗号
      if(commaPosition != -1)//如果有逗号存在就向下执行
      {
         inString = message_udp.substring(0,commaPosition);//打印出第一个逗号位置的字符串
         message_udp = message_udp.substring(commaPosition+1, message_udp.length());//打印字符串，从当前位置+1开始
      }
      else
      { //找到最后一个逗号，如果后面还有文字，就打印出来
         inString = message_udp;  
      }
      //将读取到的数据存入melody和duration数组
      Serial.println(inString);
      switch(m_d){
        case 0: melody[length_a] = inString.toInt();m_d = 1;break;
        case 1: noteDurations[length_a] = inString.toFloat();m_d = 0;length_a++;break;
      }
      //将读取到的数据存入melody和duration数组
    }while(commaPosition >=0);
  }
}
