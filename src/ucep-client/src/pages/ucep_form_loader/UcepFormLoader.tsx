import "./UcepFormLoader.css";

import Form from "@rjsf/core";
import validator from "@rjsf/validator-ajv8";
import axios, { AxiosResponse } from "axios";
import { useEffect, useState } from "react";
import * as React from "react";

import AppTab from "../../components/AppTab";
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
  const [applicationName, setApplicationName] = useState(
    {} /*globalJsonSchema*/
  );
  const [currentPageName, setCurrentPageName] = useState({} /*globalUiSchema*/);
  const [scriptExecutionId, setScriptExecutionId] = useState(0);
  const [jsonSchema, setJsonSchema] = useState({} /*globalJsonSchema*/);
  const [uiSchema, setUiSchema] = useState({} /*globalUiSchema*/);
  const [formData, setFormData] = useState();
  const [value, setValue] = React.useState(0);

  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
    setValue(newValue);
  };

  useEffect(() => {
    axios
      .post("http://localhost:9088/forms/startApplication", {
        applicationName: "MainApplication",
        scriptExecutionId: scriptExecutionId,
      })
      .then((schema) => {
        setJsonSchema(JSON.parse(schema.data.jsonSchema));
        setUiSchema(JSON.parse(schema.data.uiSchema));
        setApplicationName(schema.data.applicationName);
        setCurrentPageName(schema.data.currentPageName);
        setScriptExecutionId(schema.data.scriptExecutionId);
      });
  }, []);

  /** Handle submission of form data. */
  const handleSubmit = ({ formData: any }: any, event: any) => {
    console.log(JSON.stringify(formData));
    axios
      .post("http://localhost:9088/forms/nextActionHandler", {
        formAnswers: JSON.stringify(formData),
        applicationName: applicationName,
        currentPageName: currentPageName,
        scriptExecutionId: scriptExecutionId,
      })
      .then((schema) => {
        setJsonSchema(JSON.parse(schema.data.jsonSchema));
        setUiSchema(JSON.parse(schema.data.uiSchema));
        setApplicationName(schema.data.applicationName);
        setCurrentPageName(schema.data.currentPageName);
        setScriptExecutionId(schema.data.scriptExecutionId);
        setFormData(schema.data.formAnswers);
      })
      .catch((err) => {
        console.log("Form was not submitted.");
        console.log(err);
      });
  };

  /** Handle submission of form data. */
  const handlePrevious = ({ formData: any }: any, event: any) => {
    console.log(JSON.stringify(formData));
    axios
      .post("http://localhost:9088/forms/previousActionHandler", {
        formAnswers: JSON.stringify(formData),
        applicationName: applicationName,
        currentPageName: currentPageName,
        scriptExecutionId: scriptExecutionId,
      })
      .then((schema: AxiosResponse<any>) => {
        setJsonSchema(JSON.parse(schema.data.jsonSchema));
        setUiSchema(JSON.parse(schema.data.uiSchema));
        setApplicationName(schema.data.applicationName);
        setCurrentPageName(schema.data.currentPageName);
        setScriptExecutionId(schema.data.scriptExecutionId);
        setFormData(schema.data.formAnswers);
      })
      .catch((err) => {
        console.log("Form was not submitted.");
        console.log(err);
      });
  };
  return (
    <>
      <h1 className="text-6xl flex justify-center">UCEP Form Loader</h1>
      <AppTab
        activeTabIndex={3}
        tabs={[
          { title: "Form 1" },
          { title: "Form 2" },
          { title: "Form 3" },
          { title: "Form 4" },
        ]}
      />
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
          //onPrevious={handlePrevious}
        />
      </div>
    </>
  );
};

export default UcefFormLoader;
