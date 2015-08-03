package org.verapdf.model.impl.pb.operator.base;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSNumber;
import org.verapdf.model.GenericModelObject;
import org.verapdf.model.coslayer.CosReal;
import org.verapdf.model.impl.pb.cos.PBCosReal;
import org.verapdf.model.operator.Operator;
import org.verapdf.model.tools.IDGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base class for operator layer
 * 
 * @author Timur Kamalov
 */
public abstract class PBOperator extends GenericModelObject implements Operator {

    public static final int MAX_NUMBER_OF_ELEMENTS = 1;
    public static final String OP_TYPE_PB = "Operator";
    protected final List<COSBase> arguments;
    private final String id;
    private final String type;

    protected PBOperator(List<COSBase> arguments) {
        this(arguments, OP_TYPE_PB);
    }

    protected PBOperator(List<COSBase> arguments, final String opType) {
        this.id = IDGenerator.generateID();
        this.type = opType;
        this.arguments = Collections.unmodifiableList(arguments);
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    protected List<CosReal> getLastReal() {
        List<CosReal> cosReals = new ArrayList<>(MAX_NUMBER_OF_ELEMENTS);
        COSBase base = !arguments.isEmpty() ? arguments
                .get(arguments.size() - 1) : null;
        if (base instanceof COSNumber) {
            cosReals.add(new PBCosReal((COSNumber) base));
        }
        return cosReals;
    }

}
