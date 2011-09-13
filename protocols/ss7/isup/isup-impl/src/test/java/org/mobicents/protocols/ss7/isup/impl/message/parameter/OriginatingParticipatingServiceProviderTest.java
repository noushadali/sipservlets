/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.protocols.ss7.isup.impl.message.parameter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.mobicents.protocols.ss7.isup.ISUPComponent;
import org.mobicents.protocols.ss7.isup.ParameterRangeInvalidException;

/**
 * Start time:14:11:03 2009-04-23<br>
 * Project: mobicents-isup-stack<br>
 * 
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski
 *         </a>
 */
public class OriginatingParticipatingServiceProviderTest extends ParameterHarness {

	/**
	 * @throws IOException
	 */
	public OriginatingParticipatingServiceProviderTest() throws IOException {
		
		super.badBodies.add(getBody3());
		super.badBodies.add(getBody4());
		super.badBodies.add(getBody5());

		super.goodBodies.add(new byte[1]);
		super.goodBodies.add(getBody1());
	
	}

	private byte[] getBody1() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// we will use odd number of digits, so we leave zero as MSB

		bos.write(3);
		bos.write(super.getSixDigits());
		return bos.toByteArray();
	}

	

	public void testBody1EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException, ParameterRangeInvalidException {
		OriginatingParticipatingServiceProviderImpl bci = new OriginatingParticipatingServiceProviderImpl(getBody1());
	
		String[] methodNames = { "isOddFlag", "getAddress", "getOpspLengthIndicator" };
		Object[] expectedValues = {  false, super.getSixDigitsString(),3 };
		super.testValues(bci, methodNames, expectedValues);
	}


	
	private byte[] getBody2() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// we will use odd number of digits, so we leave zero as MSB

		bos.write(3 | 0x80);
		bos.write(super.getFiveDigits());
		return bos.toByteArray();
	}

	

	public void testBody2EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException, ParameterRangeInvalidException {
		OriginatingParticipatingServiceProviderImpl bci = new OriginatingParticipatingServiceProviderImpl(getBody2());
	
		String[] methodNames = { "isOddFlag", "getAddress", "getOpspLengthIndicator" };
		Object[] expectedValues = {  true, super.getFiveDigitsString(),3 };
		super.testValues(bci, methodNames, expectedValues);
	}

	
	
	
	private byte[] getBody3() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// we will use odd number of digits, so we leave zero as MSB

		bos.write(5 | 0x80);
		bos.write(super.getFiveDigits());
		return bos.toByteArray();
	}
	private byte[] getBody4() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// we will use odd number of digits, so we leave zero as MSB

		bos.write(2 );
		bos.write(super.getSixDigits());
		return bos.toByteArray();
	}
	private byte[] getBody5() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// we will use odd number of digits, so we leave zero as MSB

		bos.write(3 );
		bos.write(new byte[]{1,2,3,4,5});
		return bos.toByteArray();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.isup.messages.parameters.ParameterHarness#getTestedComponent
	 * ()
	 */
	@Override
	public ISUPComponent getTestedComponent() {
		return new OriginatingParticipatingServiceProviderImpl("1234");
	}

}