package com.dss.immutability;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

public class ImmutableFoo {

    private final String bar;
    private final String baz;

    private ImmutableFoo(String bar, String baz) {
        this.bar = bar;
        this.baz = baz;
    }

    private ImmutableFoo(Builder builder) {
        this.bar = builder.bar;
        this.baz = builder.baz;
    }

    public String getBar() {
        return bar;
    }

    public String getBaz() {
        return baz;
    }

	public static class Builder{

		private String bar;
		private String baz;
		
		public Builder withBar(String bar) {
		  this.bar = bar;
		  return this;
		}
		
		public Builder withBaz(String baz) {
		  this.baz = baz;
		  return this;
		}
		
		public ImmutableFoo build() {
		  validate();
		  return new ImmutableFoo(this);
		}
		
		private void validate() {
		  Preconditions.checkArgument(!StringUtils.isBlank(bar), "bar may not be blank");
		  Preconditions.checkArgument(!StringUtils.isBlank(baz), "baz may not be blank");
		}
	}

    
}
