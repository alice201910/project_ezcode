package org.ezcode.demo.security;

import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

/**
 * GooglePrincipalExtractor
 */
public class GooglePrincipalExtractor implements PrincipalExtractor {

    @Override
    public Object extractPrincipal(Map<String, Object> arg0) {
        
        return null;
    }
    
}