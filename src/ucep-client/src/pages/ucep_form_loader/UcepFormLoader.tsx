import "./UcepFormLoader.css";

import Form from "@rjsf/core";
import validator from "@rjsf/validator-ajv8";
import axios from "axios";
import { useEffect, useState } from "react";

import {
  BaseInputTemplate,
  FieldErrorTemplate,
  FieldTemplate,
  ObjectFieldTemplate,
  SubmitButton,
} from "../../components/RjsfTemplates";

const globalJsonSchema = {
  name: "PersonalInfoQuestionPage",
  title: "Personal Information",
  description: "Question page for personal information.",
  type: "object",
  required: ["Person.firstName", "Person.lastName", "Person.ssn"],
  properties: {
    "Person.firstName": {
      title: "What is your First Name?",
      type: "string",
    },
    "Person.lastName": {
      title: "What is your Last Name?",
      type: "string",
    },
    "Person.ssn": {
      title: "What is your Social Insurance Number?",
      type: "string",
    },
  },
};

const globalButtonsSchema = {
  totalForms: 1,
  buttons: {
    prvious: {
      title: "Previous",
    },
    next: {
      title: "Next",
    },
  },
};

const globalUiSchema = {
  "ui:submitButtonOptions": {
    submitText: "Next",
  },
  "Person.firstName": {
    "ui:autofocus": true,
    "ui:emptyValue": "",
    "ui:autocomplete": "family-name",
  },
  "Person.lastName": {
    "ui:emptyValue": "",
    "ui:autocomplete": "given-name",
  },
  "Person.ssn": {
    "ui:emptyValue": "",
    "ui:autocomplete": "ssn",
  },
};

const UcefFormLoader = () => {
  const [jsonSchema, setJsonSchema] = useState({} /*globalJsonSchema*/);
  const [uiSchema, setUiSchema] = useState({} /*globalUiSchema*/);
  const [formData, setFormData] = useState();

  useEffect(() => {
    axios
      .post("http://localhost:9088/forms/startApplication")
      .then((schema) => {
        setJsonSchema(JSON.parse(schema.data.jsonSchema));
        setUiSchema(JSON.parse(schema.data.uiSchema));
      });
  }, []);

  /** Handle submission of form data. */
  const handleSubmit = ({ formData: any }: any, event: any) => {
    axios.post("").catch((err) => {
      console.log("Form was not submitted.");
      console.log(err);
    });
  };

  return (
    <>
      <h1 className="text-6xl flex justify-center">UCEP Form Loader</h1>
      <div className="md:container md:mx-auto">
        <Form
          className="bg-white rounded-3xl p-10 neu-form"
          schema={jsonSchema as any}
          uiSchema={uiSchema}
          validator={validator}
          onChange={(e) => setFormData(e.formData)}
          formData={formData}
          templates={{
            ObjectFieldTemplate: ObjectFieldTemplate,
            FieldTemplate: FieldTemplate,
            FieldErrorTemplate: FieldErrorTemplate,
            BaseInputTemplate: BaseInputTemplate,
            ButtonTemplates: { SubmitButton: SubmitButton },
          }}
          onSubmit={handleSubmit}
        />
      </div>
    </>
  );
};

export default UcefFormLoader;
