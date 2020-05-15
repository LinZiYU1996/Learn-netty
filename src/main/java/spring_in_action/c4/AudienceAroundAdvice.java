package spring_in_action.c4;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AudienceAroundAdvice implements MethodInterceptor {
  public Object invoke(MethodInvocation invocation) throws Throwable {

    try {
      audience.takeSeats();
      audience.turnOffCellPhones();

      Object returnValue = invocation.proceed();
    
      audience.applaud();
    
      return returnValue;
    } catch (PerformanceException throwable) {
      audience.demandRefund();
      
      throw throwable;
    }
  }
  
  // injected
  private Audience audience;
  public void setAudience(Audience audience) {
    this.audience = audience;
  }
}
