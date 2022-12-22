package ma.enset.service;

import ma.enset.aspects.Log;
import ma.enset.aspects.SecuredByAspect;
import org.springframework.stereotype.Service;

@Service
public class MetierImpl implements IMetier {
    @Override
    @Log
    @SecuredByAspect(roles={"USER","ADMIN"})
    public void process() {
        System.out.println("Business process...");
    }

    @Override
    @SecuredByAspect(roles={"ADMIN"})
    public double compute() {
        double data=78;
        System.out.println("Business computing and returning...");
        return data;
    }
}
