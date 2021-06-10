package org.ZYX.demo.jvm.instructions.references;

import org.ZYX.demo.jvm.instructions.base.InstructionIndex16;
import org.ZYX.demo.jvm.rtda.Frame;
import org.ZYX.demo.jvm.rtda.OperandStack;
import org.ZYX.demo.jvm.rtda.heap.constantpool.FieldRef;
import org.ZYX.demo.jvm.rtda.heap.constantpool.RunTimeConstantPool;
import org.ZYX.demo.jvm.rtda.heap.methodarea.Class;
import org.ZYX.demo.jvm.rtda.heap.methodarea.Field;
import org.ZYX.demo.jvm.rtda.heap.methodarea.Method;
import org.ZYX.demo.jvm.rtda.heap.methodarea.Slots;

public class PUT_STATIC extends InstructionIndex16 {

    @Override
    public void execute(Frame frame) {
        Method currentMethod = frame.method();
        Class currentClazz = currentMethod.clazz();
        RunTimeConstantPool runTimeConstantPool = currentClazz.constantPool();
        FieldRef fieldRef = (FieldRef) runTimeConstantPool.getConstants(this.idx);
        Field field = fieldRef.resolvedField();
        Class clazz = field.clazz();

        String descriptor = field.descriptor();
        int slotId = field.slotId();
        Slots slots = clazz.staticVars();
        OperandStack stack = frame.operandStack();
        switch (descriptor.substring(0, 1)) {
            case "Z":
            case "B":
            case "C":
            case "S":
            case "I":
                slots.setInt(slotId, stack.popInt());
                break;
            case "F":
                slots.setFloat(slotId, stack.popFloat());
                break;
            case "J":
                slots.setLong(slotId, stack.popLong());
                break;
            case "D":
                slots.setDouble(slotId, stack.popDouble());
                break;
            case "L":
            case "[":
                slots.setRef(slotId, stack.popRef());
                break;
            default:
                break;
        }
    }

}
